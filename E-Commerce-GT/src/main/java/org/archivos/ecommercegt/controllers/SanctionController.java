package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.SanctionRequest;
import org.archivos.ecommercegt.services.SanctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/sanction")
@RequiredArgsConstructor
public class SanctionController {

    private final SanctionService sanctionService;

    @PostMapping()
    public ResponseEntity<?> postSanction(
            @RequestBody SanctionRequest sanctionRequest
    ) {
        try{
            sanctionService.save(sanctionRequest);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
