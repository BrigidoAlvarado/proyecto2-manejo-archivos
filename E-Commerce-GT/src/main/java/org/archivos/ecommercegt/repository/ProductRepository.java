package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
