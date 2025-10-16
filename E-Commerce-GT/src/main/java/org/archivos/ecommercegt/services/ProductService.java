package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.BasicCatalogProduct;
import org.archivos.ecommercegt.dto.BasicProduct;
import org.archivos.ecommercegt.dto.ProductRequest;
import org.archivos.ecommercegt.dto.ProductResponse;
import org.archivos.ecommercegt.models.Category;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductCategoryService productCategoryService;

    @Transactional
    public void saveProduct(ProductRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Usuario no encontrado"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setIsNew(request.isNew());
        product.setUser(user);

        MultipartFile imageFile = request.getImage();
        if (imageFile == null || imageFile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo guardar la imagen o no se encontró");
        }

        if (imageFile.getSize() > 5 * 1024 * 1024) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen excede el tamaño permitido (5 MB)");
        }

        try {
            product.setImage(imageFile.getBytes());
            // todo DELETE PRINT
            System.out.println("Se cargo la imagen al producto");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer la imagen", e);
        }

        Product productSaved = productRepository.save(product);

        productCategoryService.saveProductCategories(request.getCategories(), productSaved);

    }

    public List<BasicProduct> findAllNoApproved() {
        return productRepository.findAllNoApproved();
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        List<String> categories = product.getCategories()
                .stream()
                .map(Category::getName)
                .toList();

        String imageBase64 =  "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage());

        return ProductResponse.builder()
                .id(product.getId())
                .name( product.getName())
                .description( product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .image( imageBase64 )
                .categories( categories )
                .build();
    }

    public void approveProduct(int id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        product.setApproved( true );

        System.out.println("se aprobo el producto");

        productRepository.save(product);
    }

    public List<BasicCatalogProduct> getAllBasicApprovedProducts() {
        List<Product> products = productRepository.findByIsApprovedTrueAndStockGreaterThan(0);
        List<BasicCatalogProduct> basicProducts = new ArrayList<>();

        for (Product product : products) {

            String imageBase64 = getImageBase64(product);

            basicProducts.add( BasicCatalogProduct.builder()
                    .id( product.getId() )
                    .name( product.getName() )
                    .price( product.getPrice() )
                    .image( imageBase64 )
                    .build());
        }
        return basicProducts;
    }

    private String getImageBase64(Product product) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage());
    }
}