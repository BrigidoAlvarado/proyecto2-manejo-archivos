package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.product.*;
import org.archivos.ecommercegt.models.Category;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.services.utilities.ImageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryService productCategoryService;
    private final ImageService imageService;
    private final UserService userService;
    private final NotificationService notificationService;

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

    public ProductResponse getProductResponseById(int id) {
        Product product = getProductById(id);
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

    public Product getProductById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

    }

    @Transactional
    public void approveProduct(ApproveProductRequest approveProductRequest) {

        Product product = productRepository
                .findById(approveProductRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        product.setRevised(true);

        if (approveProductRequest.getIsApprove()){
            product.setApproved( true );
            notificationService.notifyApproveProduct(product);
        } else {
            notificationService.notifyNoApproveProduct(product);
        }
        productRepository.save(product);
    }

    public List<BasicCatalogProduct> getAllBasicApprovedProducts() {
        final String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<Product> products = productRepository.findByIsRevisedTrueAndIsApprovedTrueAndStockGreaterThan(0);
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

    public List<MoreSellingProduct> getMoreSellingProducts(String startDate, String endDate) {
        try{
            Instant start = null;
            Instant end = null;
            if ( startDate != null ) start = LocalDate.parse(startDate).atStartOfDay( ZoneId.systemDefault()).toInstant();
            if ( endDate != null ) end = LocalDate.parse(endDate).atStartOfDay(ZoneId.systemDefault()).toInstant() ;

            System.out.println("se filtra por:");
            System.out.println("start: " + start);
            System.out.println("end: " + end);

            final Pageable pageable = PageRequest.of(0, 10);
            return productRepository.getMoreSellingProducts(start, end, pageable);
        }catch (IllegalArgumentException e){
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha invalida");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<BasicProduct> findNoApproveAndRevised() {
        List<Product> products = productRepository.findByIsRevisedTrueAndIsApprovedFalse();
        List<BasicProduct> basicProducts = new ArrayList<>();

        for (Product product : products) {
            basicProducts.add(
              BasicProduct.builder()
                      .id( product.getId() )
                      .name( product.getName() )
                      .user( product.getUser().getEmail() )
                      .build()
            );
        }
        return basicProducts;
    }
}