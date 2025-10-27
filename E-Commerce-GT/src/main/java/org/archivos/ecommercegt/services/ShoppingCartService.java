package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.card.PayCardRequest;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ShoppingCartRepository;
import org.archivos.ecommercegt.services.utilities.ParseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/**
 * The type Shopping cart service.
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    /**
     * The constant PERCENT_PRODUCT_OWNER_EARNING.
     */
    public static final double PERCENT_PRODUCT_OWNER_EARNING = 0.95;
    /**
     * The constant PERCENT_APP_EARNING.
     */
    public static final double PERCENT_APP_EARNING   = 0.05;

    private final ShoppingCartRepository shoppingCartRepository;

    private final ParseService shoppingCartTools;
    private final WalletService walletService;
    private final UserService userService;
    private final CreditCardService creditCardService;
    private final DeliveryPackageService deliveryPackageService;
    private final PurchaseDetailService purchaseDetailService;

    /**
     * Save shopping cart.
     *
     * @param user the user
     * @return the shopping cart
     */
    public ShoppingCart save(User user) {

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setStatus(true);

        return shoppingCartRepository.save(cart);
    }

    /**
     * Get current shopping cart shopping cart.
     *
     * @return the shopping cart
     */
    public ShoppingCart getCurrentShoppingCart(){
        final User user = userService.getUser();

        return  shoppingCartRepository
                .findByStatusAndUser(true, user)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "ShoppingCart not found"));
    }

    /**
     * Get current shopping cart response shopping cart response.
     *
     * @return the shopping cart response
     */
    public ShoppingCartResponse getCurrentShoppingCartResponse(){

        final ShoppingCart cart = getCurrentShoppingCart();
        return shoppingCartTools.parseShoppingCartResponse(cart);
    }

    /**
     * Get shopping cart response by id shopping cart response.
     *
     * @param id the id
     * @return the shopping cart response
     */
    public ShoppingCartResponse getShoppingCartResponseById(int id){
        final ShoppingCart cart = getShoppingCartById(id);
        return shoppingCartTools.parseShoppingCartResponse(cart);
    }

    /**
     * Pay shopping cart.
     *
     * @param request the request
     */
    @Transactional
    public void payShoppingCart(PayCardRequest request){

        final User user = userService.getUser();

        //guardar y validar la tarjeta
        if (request.isSave()){
            creditCardService.save( request.getCardNumber() ,user);
        } else {
            creditCardService.validNumber(request.getCardNumber());
        }

        // obtener el carrito actual
        ShoppingCart cart = getCurrentShoppingCart();

        // pagar cada producto
        double total = 0;
        double totalPrice = 0;
        for (PurchaseDetail purchaseDetail : cart.getPurchaseDetails()){
            final User productOwner = purchaseDetail.getProduct().getUser();
            final double price = purchaseDetail.getProduct().getPrice();
            final double amount = purchaseDetail.getAmount();
            final double subTotal = amount * price;
            walletService.updateMoney( productOwner, subTotal * PERCENT_PRODUCT_OWNER_EARNING);
            total +=  subTotal * PERCENT_APP_EARNING;
            totalPrice += subTotal;
        }

        walletService.updateAppMoney(total);

        // desactivar carrito
        cart.setStatus(false);
        shoppingCartRepository.save(cart);

        // crear un nuevo paquete
        deliveryPackageService.save(cart, totalPrice);

        // crear un nuevo carrito
        ShoppingCart newCart = new ShoppingCart();
        newCart.setUser(user);
        newCart.setStatus(true);
        shoppingCartRepository.save(newCart);
    }

    /**
     * Delete shopping cart items.
     *
     * @param id the id
     */
    @Transactional
    public void deleteShoppingCartItems(int id){
        final ShoppingCart cart = getShoppingCartById(id);
        final List<PurchaseDetail> purchaseDetails = cart.getPurchaseDetails();

        for (PurchaseDetail purchaseDetail : purchaseDetails){
            purchaseDetailService.deletePurchaseDetail(purchaseDetail);
        }
        shoppingCartRepository.delete(cart);
        ShoppingCart newCart = new ShoppingCart();
        newCart.setUser(userService.getUser());
        newCart.setStatus(true);
        shoppingCartRepository.save(newCart);
    }

    private ShoppingCart getShoppingCartById(int id){
        return shoppingCartRepository.findById(id)
                .orElseThrow(
                        () -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "ShoppingCart not found")
                );
    }

}
