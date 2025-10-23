package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.deliveryPackage.DeliveryPackageResponse;
import org.archivos.ecommercegt.services.DeliveryPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( ApplicationConfig.BASE_URL + "/delivery-package")
@RequiredArgsConstructor
public class DeliveryPackageController {

    private final DeliveryPackageService deliveryPackageService;

    @GetMapping
    public ResponseEntity<List<DeliveryPackageResponse>> getPackagesInProcess(){
        List<DeliveryPackageResponse> deliveryPackageResponseList = deliveryPackageService.getAllDeliveryPackagesInProcess();
        return ResponseEntity.ok(deliveryPackageResponseList);
    }

    @GetMapping("/no-revised")
    public ResponseEntity< List< DeliveryPackageResponse > > getNoRevised(){
        List<DeliveryPackageResponse>  deliveryPackageResponseList = deliveryPackageService.getAllDeliveryPackagesNoRevised();
        return ResponseEntity.ok(deliveryPackageResponseList);
    }

    @PatchMapping("/deliver/{id}")
    public ResponseEntity<List<DeliveryPackageResponse>> deliverPackage(
            @PathVariable int id
    ){
        deliveryPackageService.deliverPackage(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/revised/{id}")
    ResponseEntity<?> patchRevised(
            @PathVariable int id
    ){
        deliveryPackageService.revisedDeliveryPackage(id);
        return ResponseEntity.ok().build();
    }
}
