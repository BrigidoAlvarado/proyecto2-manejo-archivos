package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.card.CreditCardResponse;
import org.archivos.ecommercegt.models.CreditCard;
import org.archivos.ecommercegt.services.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/card")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCardsByUser() {
        List<CreditCardResponse> cards =  creditCardService.findAllByUser();
        return ResponseEntity.ok(cards);
    }
}
