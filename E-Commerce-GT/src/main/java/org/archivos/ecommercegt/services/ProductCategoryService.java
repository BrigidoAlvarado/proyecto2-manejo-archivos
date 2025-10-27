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

/**
 * The type Product category service.
 */
@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    /**
     * Save product categories.
     *
     * @param categories the categories
     * @param product    the product
     * @throws HttpStatusCodeException the http status code exception
     */
    public void saveProductCategories(List<String> categories, Product product) throws HttpStatusCodeException {

        for (String category : categories) {
            if (!category.isBlank()) saveProductCategory(category, product);
        }
    }

    /**
     * Save product category.
     *
     * @param categoryName the category name
     * @param product      the product
     * @throws HttpStatusCodeException the http status code exception
     */
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

    /**
     * Delete categories in product.
     *
     * @param categories the categories
     * @param productId  the product id
     */
    public void deleteCategoriesInProduct(List<Category> categories, int productId) {
        for (Category category : categories) {
            productCategoryRepository.deleteProductCategoriesByCategoryAndProductId( category, productId );
        }
    }
}
