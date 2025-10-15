package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.BasicProduct;
import org.archivos.ecommercegt.dto.ProductRequest;
import org.archivos.ecommercegt.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

import java.util.List;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(
            @ModelAttribute ProductRequest productRequest
    ) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();

            productService.saveProduct(productRequest, userEmail);

            return ResponseEntity.ok().build();

        } catch (MultipartException ex) {

            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al processar la imagen");

        } catch (Exception ex) {

            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al guardar el producto");
        }
    }

    @GetMapping("/noApproved")
    public ResponseEntity<List<BasicProduct>> getNoApprovedProducts(){
        return ResponseEntity.ok(productService.findAllNoApproved());
    }
}
