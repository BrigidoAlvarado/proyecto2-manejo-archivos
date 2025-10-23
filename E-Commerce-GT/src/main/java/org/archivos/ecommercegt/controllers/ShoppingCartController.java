package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.card.PayCardRequest;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<ShoppingCartResponse> getShoppingCart() {
        ShoppingCartResponse shoppingCartResponse = shoppingCartService.getCurrentShoppingCartResponse();
        return ResponseEntity.ok(shoppingCartResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCartById(
            @PathVariable Integer id
    ) {
        ShoppingCartResponse shoppingCartResponse = shoppingCartService.getShoppingCartResponseById(id);
        return ResponseEntity.ok(shoppingCartResponse);
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payShoppingCard(
            @RequestBody PayCardRequest payCardRequest
    ) {
       try{
           shoppingCartService.payShoppingCart(payCardRequest);
           return ResponseEntity.ok().build();
       }catch(Exception e){
           e.printStackTrace();
           throw e;
       }
    }

}
