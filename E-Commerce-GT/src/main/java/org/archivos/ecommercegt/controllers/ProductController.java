package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.ReportRequest;
import org.archivos.ecommercegt.dto.product.*;
import org.archivos.ecommercegt.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

import java.security.Timestamp;
import java.util.List;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/noApproved")
    public ResponseEntity<List<BasicProduct>> getNoApprovedProducts(){
        return ResponseEntity.ok(productService.findAllNoApproved());
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int id){
        ProductResponse productResp = productService.getProductById(id);
        return ResponseEntity.ok(productResp);
    }

    @GetMapping("/approved/available")
    public ResponseEntity<List<BasicCatalogProduct>> getAllBasicApprovedProducts(){
        List<BasicCatalogProduct> products = productService.getAllBasicApprovedProducts();
        return ResponseEntity.ok( products );
    }

    @GetMapping("/top/selling")
    public ResponseEntity<List<MoreSellingProduct>> getTopSellingProducts(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ){
        List<MoreSellingProduct> topSelling = productService.getMoreSellingProducts(startDate, endDate);
        return ResponseEntity.ok(topSelling);
    }

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

    @PatchMapping("/approve/{id}")
    public ResponseEntity<?> approveProduct(@PathVariable int id){
        productService.approveProduct(id);
        return ResponseEntity.ok().build();
    }
}
