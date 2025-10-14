package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.ProductRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL +"/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping( consumes =  {"multipart/form-data"})
    public ResponseEntity<?> create(
            @ModelAttribute ProductRequest productRequest
    ) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();

            // todo DELETE PRINT
            System.out.println("userEmail: " + userEmail);

            Product saved = productService.saveProduct(productRequest, userEmail);
            return ResponseEntity.ok(saved);

        }catch(MultipartException ex){

            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al processar la imagen");

        }catch(Exception ex){

            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al guardar el producto");
        }
    }
}
