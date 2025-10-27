package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.qualification.QualificationRequest;
import org.archivos.ecommercegt.services.QualificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Qualification controller.
 */
@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/qualification")
@RequiredArgsConstructor
public class QualificationController {

    private final QualificationService qualificationService;

    /**
     * Post qualification response entity.
     *
     * @param qualification the qualification
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<?> postQualification(
            @RequestBody QualificationRequest qualification) {
        qualificationService.save(qualification);
        return ResponseEntity.ok().build();
    }
}
