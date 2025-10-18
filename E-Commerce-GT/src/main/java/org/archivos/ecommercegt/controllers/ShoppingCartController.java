package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.card.PayCardRequest;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<ShoppingCartResponse> getShoppingCart() {
        ShoppingCartResponse shoppingCartResponse = shoppingCartService.getShoppingCartResponse();
        return ResponseEntity.ok(shoppingCartResponse);
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payShoppingCard(
            @RequestBody PayCardRequest payCardRequest
    ) {
        shoppingCartService.payShoppingCart(payCardRequest);
        return ResponseEntity.ok().build();
    }

}
