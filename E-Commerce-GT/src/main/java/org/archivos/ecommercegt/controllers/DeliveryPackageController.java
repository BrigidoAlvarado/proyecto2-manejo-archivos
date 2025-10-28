package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.deliveryPackage.DeliveryPackageResponse;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.services.DeliveryPackageService;
import org.archivos.ecommercegt.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Delivery package controller.
 */
@RestController
@RequestMapping( ApplicationConfig.BASE_URL + "/delivery-package")
@RequiredArgsConstructor
public class DeliveryPackageController {

    private final DeliveryPackageService deliveryPackageService;
    private final UserService userService;

    /**
     * Get packages in process response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<DeliveryPackageResponse>> getPackagesInProcess(){
        List<DeliveryPackageResponse> deliveryPackageResponseList = deliveryPackageService.getAllDeliveryPackagesInProcess();
        return ResponseEntity.ok(deliveryPackageResponseList);
    }

    /**
     * Get package by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getPackageById(
            @PathVariable int id
    ){
        final ShoppingCartResponse shoppingCartResponse = deliveryPackageService.getPackageDetails(id);
        return ResponseEntity.ok(shoppingCartResponse);
    }

    /**
     * Get no revised response entity.
     *
     * @return the response entity
     */
    @GetMapping("/no-revised")
    public ResponseEntity< List< DeliveryPackageResponse > > getNoRevised(){
        List<DeliveryPackageResponse>  deliveryPackageResponseList = deliveryPackageService.getAllDeliveryPackagesNoRevised();
        return ResponseEntity.ok(deliveryPackageResponseList);
    }

    /**
     * Get user response entity.
     *
     * @return the response entity
     */
    @GetMapping("/user")
    public ResponseEntity<List<DeliveryPackageResponse>> getUser(){
        User user = userService.getUser();
        List<DeliveryPackageResponse> deliveryPackageResponses = deliveryPackageService.getAllByUser(user);
        return ResponseEntity.ok(deliveryPackageResponses);
    }

    /**
     * Deliver package response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PatchMapping("/deliver/{id}")
    public ResponseEntity<List<DeliveryPackageResponse>> deliverPackage(
            @PathVariable int id
    ){
        deliveryPackageService.deliverPackage(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Patch revised response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PatchMapping("/revised/{id}")
    ResponseEntity<?> patchRevised(
            @PathVariable int id
    ){
        deliveryPackageService.revisedDeliveryPackage(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Patch date response entity.
     *
     * @param id   the id
     * @param date the date
     * @return the response entity
     */
    @PatchMapping("/date/{id}")
    ResponseEntity<List<DeliveryPackageResponse>> patchDate(
            @PathVariable int id,
            @RequestBody String date
    ){
        deliveryPackageService.patchDeliveryDate(id, date);
        return ResponseEntity.ok().build();
    }
}
