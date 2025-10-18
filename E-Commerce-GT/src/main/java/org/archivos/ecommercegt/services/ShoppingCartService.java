package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.card.PayCardRequest;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailResponse;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ShoppingCartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    public static final double PERCENT_PRODUCT_OWNER_EARNING = 0.95;
    public static final double PERCENT_APP_EARNING   = 0.05;

    private final ShoppingCartRepository shoppingCartRepository;

    private final WalletService walletService;
    private final UserService userService;
    private final CreditCardService creditCardService;

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

    public ShoppingCartResponse getShoppingCartResponse(){

        final ShoppingCart cart = getCurrentShoppingCart();
        final List<PurchaseDetailResponse> purchaseDetailResponses = new ArrayList<>();

        double total = 0;
        for(PurchaseDetail purchaseDetail : cart.getPurchaseDetails()){

            double subTotal =  purchaseDetail.getProduct().getPrice() * purchaseDetail.getAmount();
            total = total + subTotal;

            purchaseDetailResponses.add(
                    PurchaseDetailResponse.builder()
                            .price( purchaseDetail.getProduct().getPrice() )
                            .amount( purchaseDetail.getAmount() )
                            .productId( purchaseDetail.getProduct().getId() )
                            .name( purchaseDetail.getProduct().getName() )
                            .subTotal( subTotal )
                            .build()
            );
        }

        return ShoppingCartResponse.builder()
                .id( cart.getId() )
                .purchaseDetails( purchaseDetailResponses )
                .total(total)
                .build();
    }

    @Transactional
    public void payShoppingCart(PayCardRequest request){

        System.out.println("En el request hay: "+request.getCardNumber());

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
        for (PurchaseDetail purchaseDetail : cart.getPurchaseDetails()){
            final User productOwner = purchaseDetail.getProduct().getUser();
            final double price = purchaseDetail.getProduct().getPrice();
            final double amount = purchaseDetail.getAmount();
            final double subTotal = amount * price;
            walletService.updateMoney( productOwner, subTotal * PERCENT_PRODUCT_OWNER_EARNING);
            total +=  subTotal * PERCENT_APP_EARNING;
        }

        walletService.updateAppMoney(total);

        // desactivar carrito
        cart.setStatus(false);
        shoppingCartRepository.save(cart);

        // crear un nuevo carrito
        ShoppingCart newCart = new ShoppingCart();
        newCart.setUser(user);
        newCart.setStatus(true);
        shoppingCartRepository.save(newCart);
    }
}
