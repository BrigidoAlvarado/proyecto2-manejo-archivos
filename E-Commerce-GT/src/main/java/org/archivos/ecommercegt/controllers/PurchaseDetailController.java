package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailRequest;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.services.PurchaseDetailService;
import org.archivos.ecommercegt.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/purchase-detail")
@RequiredArgsConstructor
public class PurchaseDetailController {

    private final ShoppingCartService shoppingCartService;
    private final PurchaseDetailService purchaseDetailService;

    @PostMapping
    public ResponseEntity<PurchaseDetail> save(
            @RequestBody PurchaseDetailRequest purchaseDetail
    ) {
        final ShoppingCart shoppingCart = shoppingCartService.getCurrentShoppingCart();
        purchaseDetailService.savePurchaseDetail(purchaseDetail, shoppingCart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchaseDetail(
            @PathVariable Integer id
    ){
        purchaseDetailService.deletePurchaseDetail(id);
        return ResponseEntity.ok().build();
    }
}