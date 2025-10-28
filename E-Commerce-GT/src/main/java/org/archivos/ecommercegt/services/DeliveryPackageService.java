package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.deliveryPackage.DeliveryPackageResponse;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.DeliveryPackage;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.DeliveryPackageRepository;
import org.archivos.ecommercegt.services.utilities.ParseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Delivery package service.
 */
@Service
@RequiredArgsConstructor
public class DeliveryPackageService {

    private final DeliveryPackageRepository deliveryPackageRepository;

    private final ParseService shoppingCartTools;
    private final NotificationService notificationService;

    /**
     * Save.
     *
     * @param shoppingCart the shopping cart
     * @param totalPrice   the total price
     */
    public void save(ShoppingCart shoppingCart, double totalPrice) {
        DeliveryPackage deliveryPackage = new DeliveryPackage();
        deliveryPackage.setTotal(totalPrice);
        deliveryPackage.setShoppingCart(shoppingCart);
        deliveryPackageRepository.save(deliveryPackage);
    }

    /**
     * Save delivery package.
     *
     * @param deliveryPackage the delivery package
     * @return the delivery package
     */
    public DeliveryPackage  save(DeliveryPackage  deliveryPackage){
        return deliveryPackageRepository.save( deliveryPackage );
    }

    /**
     * Get all delivery packages in process list.
     *
     * @return the list
     */
    public List<DeliveryPackageResponse> getAllDeliveryPackagesInProcess(){
        final List<DeliveryPackage>  deliveryPackages = deliveryPackageRepository.findAllByIsDelivered(false);
        final List<DeliveryPackageResponse>  deliveryPackagesResponse = new ArrayList<>();

        for(DeliveryPackage deliveryPackage : deliveryPackages){
            deliveryPackagesResponse.add(
              DeliveryPackageResponse.builder()
                      .id( deliveryPackage.getId() )
                      .userName( deliveryPackage.getShoppingCart().getUser().getUsername() )
                      .deliveryDate( deliveryPackage.getDeliveryDate() )
                      .departureDate( deliveryPackage.getDepartureDate() )
                      .isDelivered( deliveryPackage.getIsDelivered() )
                      .shoppingCart( shoppingCartTools
                              .parseShoppingCartResponse( deliveryPackage.getShoppingCart() )
                      )
                      .build()
            );
        }
        return deliveryPackagesResponse;
    }

    /**
     * Deliver package.
     *
     * @param id the id
     */
    @Transactional
    public void deliverPackage(int id){
        try{
            // obtener el paquete
            DeliveryPackage deliveryPackage = getDeliveryPackageById(id);
            // actualizar el estado
            deliveryPackage.setIsDelivered(true);
            deliveryPackage.setDeliverAt( Instant.now() );
            // acutalizar la db
            deliveryPackageRepository.save(deliveryPackage);
            // notificar entrega
            notificationService.notifyPackageStatusChange(deliveryPackage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo entregar el paquete");
        }
    }

    /**
     * Get delivery package by id delivery package.
     *
     * @param id the id
     * @return the delivery package
     */
    public DeliveryPackage getDeliveryPackageById(int id){
        return deliveryPackageRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery package not found"));

    }

    /**
     * Get all delivery packages no revised list.
     *
     * @return the list
     */
    public List<DeliveryPackageResponse> getAllDeliveryPackagesNoRevised(){
        List<DeliveryPackage> packages = deliveryPackageRepository.findAllByIsRevised(false);
        List<DeliveryPackageResponse>  deliveryPackagesResponse = new ArrayList<>();

        for(DeliveryPackage deliveryPackage : packages){
            deliveryPackagesResponse.add(
              DeliveryPackageResponse.builder()
                      .id( deliveryPackage.getId() )
                      .userName( deliveryPackage.getShoppingCart().getUser().getUsername() )
                      .userEmail( deliveryPackage.getShoppingCart().getUser().getEmail() )
                      .isDelivered( deliveryPackage.getIsDelivered() )
                      .departureDate( deliveryPackage.getDepartureDate() )
                      .deliveryDate( deliveryPackage.getDeliveryDate() )
                      .deliverAt( deliveryPackage.getDeliverAt() )
                      .shoppingCart( shoppingCartTools
                              .parseShoppingCartResponse( deliveryPackage.getShoppingCart() ) )
                      .build()
            );
        }
        return deliveryPackagesResponse;
    }

    /**
     * Revised delivery package.
     *
     * @param id the id
     */
    public void revisedDeliveryPackage(int id){
        DeliveryPackage deliveryPackage = getDeliveryPackageById(id);
        deliveryPackage.setIsRevised(true);
        deliveryPackageRepository.save(deliveryPackage);
    }

    /**
     * Patch delivery date.
     *
     * @param id   the id
     * @param date the date
     */
    public void patchDeliveryDate(int id, String date) {
        try{
            final Instant  deliveryDate = LocalDate.parse( date ).atStartOfDay( ZoneId.systemDefault() ).toInstant();
            final DeliveryPackage deliveryPackage = getDeliveryPackageById(id);
            deliveryPackage.setDeliveryDate(deliveryDate);
            deliveryPackageRepository.save(deliveryPackage);
        }catch(DateTimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }

    /**
     * Get all by user list.
     *
     * @param user the user
     * @return the list
     */
    public List<DeliveryPackageResponse> getAllByUser(User user){
        final List<DeliveryPackage> packages = deliveryPackageRepository.findByShoppingCartUser( user );
        final List<DeliveryPackageResponse>  deliveryPackagesResponse = new ArrayList<>();

        for(DeliveryPackage deliveryPackage : packages){
            deliveryPackagesResponse.add(
              DeliveryPackageResponse.builder()
                      .id( deliveryPackage.getId() )
                      .shoppingCart( shoppingCartTools.parseShoppingCartResponse( deliveryPackage.getShoppingCart()) )
                      .deliverAt( deliveryPackage.getDeliverAt() )
                      .userEmail( user.getEmail() )
                      .userName( user.getName() )
                      .deliveryDate( deliveryPackage.getDeliveryDate() )
                      .departureDate( deliveryPackage.getDepartureDate() )
                      .isDelivered( deliveryPackage.getIsDelivered() ).build()
            );
        }
        return deliveryPackagesResponse;
    }

    /**
     * Get package details shopping cart response.
     *
     * @param packageId the package id
     * @return the shopping cart response
     */
    public ShoppingCartResponse getPackageDetails(int packageId){
        final DeliveryPackage deliveryPackage = deliveryPackageRepository.findById(packageId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery package not found")
                );

        final ShoppingCart shoppingCart = deliveryPackage.getShoppingCart();
        return shoppingCartTools.parseShoppingCartResponse( deliveryPackage.getShoppingCart() );
    }
}
