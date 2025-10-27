package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.product.*;
import org.archivos.ecommercegt.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Get no approved products response entity.
     *
     * @return the response entity
     */
// Usuario moderador obtiene los no aprobados para aprobarlos
    @GetMapping("/noApproved")
    public ResponseEntity<List<BasicProduct>> getNoApprovedProducts(){
        return ResponseEntity.ok(productService.findAllNoApproved());
    }

    /**
     * Get product response entity.
     *
     * @param id the id
     * @return the response entity
     */
// Obtener producto por id
    @GetMapping("/display/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int id){
       try{
           ProductResponse productResp = productService.getProductResponseById(id);
           return ResponseEntity.ok(productResp);
       }catch (Exception e){
           e.printStackTrace();
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /**
     * Get product by id response entity.
     *
     * @return the response entity
     */
    @GetMapping("/user-selling")
    public ResponseEntity<List<BasicProduct>> getProductById(){
        List<BasicProduct> products = productService.getBasicProductsByUser();
        return ResponseEntity.ok(products);
    }

    /**
     * Get all basic approved products response entity.
     *
     * @return the response entity
     */
    @GetMapping("/approved/available")
    public ResponseEntity<List<BasicCatalogProduct>> getAllBasicApprovedProducts(){
        List<BasicCatalogProduct> products = productService.getAllBasicApprovedProducts();
        return ResponseEntity.ok( products );
    }

    /**
     * Gets top selling products.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the top selling products
     */
    @GetMapping("/top/selling")
    public ResponseEntity<List<MoreSellingProduct>> getTopSellingProducts(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ){
        List<MoreSellingProduct> topSelling = productService.getMoreSellingProducts(startDate, endDate);
        return ResponseEntity.ok(topSelling);
    }

    /**
     * Get no approve response entity.
     *
     * @return the response entity
     */
    @GetMapping("/no-approve/revised")
    public ResponseEntity<List<BasicProduct>> getNoApprove(){
        List<BasicProduct> products = productService.findNoApproveAndRevised();
        return ResponseEntity.ok(products);
    }

    /**
     * Create response entity.
     *
     * @param productRequest the product request
     * @return the response entity
     */
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

    /**
     * Approve product response entity.
     *
     * @param approveProductRequest the approve product request
     * @return the response entity
     */
    @PatchMapping("/approve")
    public ResponseEntity<?> approveProduct(
            @RequestBody ApproveProductRequest approveProductRequest
    ){
        productService.approveProduct(approveProductRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Update product response entity.
     *
     * @param productRequest the product request
     * @return the response entity
     */
    @PutMapping(
            value = "/update",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<?> updateProduct(
            @ModelAttribute ProductRequest productRequest
    ){
        System.out.println("\n\n\nsi entra\n\n\n");
        try{
            productService.updateProduct(productRequest);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar producto");
        }
    }
}
