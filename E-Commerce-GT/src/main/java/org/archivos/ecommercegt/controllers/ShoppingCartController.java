package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.card.PayCardRequest;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Shopping cart controller.
 */
@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    /**
     * Gets shopping cart.
     *
     * @return the shopping cart
     */
    @GetMapping
    public ResponseEntity<ShoppingCartResponse> getShoppingCart() {
        ShoppingCartResponse shoppingCartResponse = shoppingCartService.getCurrentShoppingCartResponse();
        return ResponseEntity.ok(shoppingCartResponse);
    }

    /**
     * Gets shopping cart by id.
     *
     * @param id the id
     * @return the shopping cart by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCartById(
            @PathVariable Integer id
    ) {
        ShoppingCartResponse shoppingCartResponse = shoppingCartService.getShoppingCartResponseById(id);
        return ResponseEntity.ok(shoppingCartResponse);
    }

    /**
     * Pay shopping card response entity.
     *
     * @param payCardRequest the pay card request
     * @return the response entity
     */
    @PostMapping("/pay")
    public ResponseEntity<?> payShoppingCard(
            @RequestBody PayCardRequest payCardRequest
    ) {
        shoppingCartService.payShoppingCart(payCardRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete shopping cart items response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteShoppingCartItems(
            @PathVariable Integer id
    ) {
        shoppingCartService.deleteShoppingCartItems(id);
        return ResponseEntity.ok().build();
    }
}
