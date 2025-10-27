package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Category;
import org.archivos.ecommercegt.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Product category repository.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * Delete product categories by category and product id.
     *
     * @param category  the category
     * @param productId the product id
     */
    void deleteProductCategoriesByCategoryAndProductId(Category category, Integer productId);
}
