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


@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    public static final double PERCENT_PRODUCT_OWNER_EARNING = 0.95;
    public static final double PERCENT_APP_EARNING   = 0.05;

    private final ShoppingCartRepository shoppingCartRepository;

    private final ParseService shoppingCartTools;
    private final WalletService walletService;
    private final UserService userService;
    private final CreditCardService creditCardService;
    private final DeliveryPackageService deliveryPackageService;

    public ShoppingCart save(User user) {

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setStatus(true);

        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart getCurrentShoppingCart(){
        final User user = userService.getUser();

        return  shoppingCartRepository
                .findByStatusAndUser(true, user)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "ShoppingCart not found"));
    }

    public ShoppingCartResponse getCurrentShoppingCartResponse(){

        final ShoppingCart cart = getCurrentShoppingCart();
        return shoppingCartTools.parseShoppingCartResponse(cart);
    }

    public ShoppingCartResponse getShoppingCartResponseById(int id){
        final ShoppingCart cart = getShoppingCartById(id);
        return shoppingCartTools.parseShoppingCartResponse(cart);
    }

    public ShoppingCart getShoppingCartById(int id){
        return shoppingCartRepository.findById(id)
                .orElseThrow(
                        () -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "ShoppingCart not found")
                );
    }
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

}
