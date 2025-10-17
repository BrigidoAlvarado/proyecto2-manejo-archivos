package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailResponse;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.models.enums.Role;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.repository.PurchaseDetailRepository;
import org.archivos.ecommercegt.repository.ShoppingCartRepository;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final UserRepository userRepository;

    public ShoppingCart save(User user) {

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setStatus(true);

        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart getCurrentShoppingCart(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return  shoppingCartRepository
                .findByStatusAndUser(true, user)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "ShoppingCart not found"));
    }

    public ShoppingCartResponse getShoppingCartResponse(){

        final ShoppingCart cart = getCurrentShoppingCart();
        final List<PurchaseDetailResponse> purchaseDetailResponses = new ArrayList<>();

        for(PurchaseDetail purchaseDetail : cart.getPurchaseDetails()){
            purchaseDetailResponses.add(
                    PurchaseDetailResponse.builder()
                            .price( purchaseDetail.getProduct().getPrice() )
                            .amount( purchaseDetail.getAmount() )
                            .productId( purchaseDetail.getProduct().getId() )
                            .name( purchaseDetail.getProduct().getName() )
                            .build()
            );
        }

        return ShoppingCartResponse.builder()
                .id( cart.getId() )
                .purchaseDetails( purchaseDetailResponses )
                .build();
    }
}
