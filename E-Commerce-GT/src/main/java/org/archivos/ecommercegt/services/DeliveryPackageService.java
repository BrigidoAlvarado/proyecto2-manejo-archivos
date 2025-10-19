package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.deliveryPackage.DeliveryPackageResponse;
import org.archivos.ecommercegt.models.DeliveryPackage;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.repository.DeliveryPackageRepository;
import org.archivos.ecommercegt.services.tools.ShoppingCartTools;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryPackageService {

    private final DeliveryPackageRepository deliveryPackageRepository;

    private final ShoppingCartTools shoppingCartTools;

    public void save(ShoppingCart shoppingCart){
        DeliveryPackage deliveryPackage = new DeliveryPackage();
        deliveryPackage.setShoppingCart(shoppingCart);
        deliveryPackageRepository.save(deliveryPackage);
    };

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

    public void deliverPackage(int id){
        //obtener el paquete
        DeliveryPackage deliveryPackage = deliveryPackageRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery package not found"));
        //actualizar el estado
        deliveryPackage.setIsDelivered(true);
        //acutalizar la db
        deliveryPackageRepository.save(deliveryPackage);
    }
}
