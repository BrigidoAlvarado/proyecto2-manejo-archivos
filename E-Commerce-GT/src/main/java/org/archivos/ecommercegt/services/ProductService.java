package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.product.BasicCatalogProduct;
import org.archivos.ecommercegt.dto.product.BasicProduct;
import org.archivos.ecommercegt.dto.product.ProductRequest;
import org.archivos.ecommercegt.dto.product.ProductResponse;
import org.archivos.ecommercegt.models.Category;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.services.utilities.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryService productCategoryService;
    private final ImageService imageService;
    private final UserService userService;

    @Transactional
    public void saveProduct(ProductRequest request, String userEmail) {
        User user = userService.getUser();

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

        // Establecer url para la imagen
        String imageUrl = imageService.getImageUrl(imageFile);
        System.out.println("La url es: " + imageUrl);
        product.setImageUrl(imageUrl);

        // Guardar imagen en el servidor
        imageService.saveImage(imageFile);

        // Guardar Product
        Product productSaved = productRepository.save(product);

        // Guardar Categorias
        productCategoryService.saveProductCategories(request.getCategories(), productSaved);
        product.setImageUrl( imageUrl );
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

        String imageBase64 = imageService.getBase64Image(product.getImageUrl());

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
        final String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<Product> products = productRepository.findByIsApprovedTrueAndStockGreaterThan(0);
        final List<BasicCatalogProduct> basicProducts = new ArrayList<>();

        for (Product product : products) {

            if(product.getUser().getEmail().equals(userEmail)) continue;

            String imageBase64 = imageService.getBase64Image(product.getImageUrl());

            basicProducts.add( BasicCatalogProduct.builder()
                    .id( product.getId() )
                    .name( product.getName() )
                    .price( product.getPrice() )
                    .image( imageBase64 )
                    .build());
        }
        return basicProducts;
    }

}