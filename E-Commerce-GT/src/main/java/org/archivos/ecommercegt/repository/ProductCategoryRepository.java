package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
