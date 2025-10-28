package org.archivos.ecommercegt.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.sanction.SanctionDeliveryPackageRequest;
import org.archivos.ecommercegt.dto.sanction.SanctionProductRequest;
import org.archivos.ecommercegt.dto.sanction.SanctionResponse;
import org.archivos.ecommercegt.services.SanctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The type Sanction controller.
 */
@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/sanction")
@RequiredArgsConstructor
public class SanctionController {

    private final SanctionService sanctionService;


    /**
     * Get sanctions by user id response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<SanctionResponse>> getSanctionsByUserId(
            @PathVariable int userId
    ){
        List<SanctionResponse> sanctionResponses = sanctionService.getSanctionsByUserId(userId);
        return ResponseEntity.ok(sanctionResponses);
    }

    /**
     * Post sanction response entity.
     *
     * @param sanctionRequest the sanction request
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<?> postSanction(
            @RequestBody SanctionProductRequest sanctionRequest
    ) {
        try{
            sanctionService.sanctionAndRejectProduct(sanctionRequest);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Post sanction deliver package response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/delivery-package")
    public ResponseEntity<?> postSanctionDeliverPackage(
            @RequestBody SanctionDeliveryPackageRequest request
            ){
        sanctionService.sanctionDeliveryPackage(request);
        return ResponseEntity.ok().build();
    }
}
