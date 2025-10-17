package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailRequest;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.services.PurchaseDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/purchase-detail")
@RequiredArgsConstructor
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @PostMapping
    public ResponseEntity<PurchaseDetail> save(
            @RequestBody PurchaseDetailRequest purchaseDetail
    ) {
        purchaseDetailService.save(purchaseDetail);
        return ResponseEntity.ok().build();
    }
}