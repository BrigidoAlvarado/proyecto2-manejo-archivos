package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
