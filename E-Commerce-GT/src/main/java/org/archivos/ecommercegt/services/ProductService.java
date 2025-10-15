package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.BasicProduct;
import org.archivos.ecommercegt.dto.ProductRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer la imagen", e);
        }

        Product productSaved = productRepository.save(product);

        productCategoryService.saveProductCategories(request.getCategories(), productSaved);

    }

    public List<BasicProduct> findAllNoApproved() {
        return productRepository.findAllNoApproved();
    }

    public Product getProductById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }
}