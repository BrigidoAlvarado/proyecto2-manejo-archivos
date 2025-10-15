package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.models.Category;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.ProductCategory;
import org.archivos.ecommercegt.repository.CategoryRepository;
import org.archivos.ecommercegt.repository.ProductCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public void saveProductCategories(List<String> categories, Product product) throws HttpStatusCodeException {

        for (String category : categories) {
            saveProductCategory(category, product);
        }
    }

    public void saveProductCategory(String categoryName, Product product) throws HttpStatusCodeException {

        Category category = categoryRepository.findById(categoryName)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "No se encontro la categoria: " + categoryName
                        )
                );

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory(category);
        productCategory.setProduct(product);

        productCategoryRepository.save(productCategory);
    }
}
