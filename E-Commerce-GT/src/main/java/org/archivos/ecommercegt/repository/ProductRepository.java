package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.BasicProduct;
import org.archivos.ecommercegt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new org.archivos.ecommercegt.dto.BasicProduct(p.name, p.user.name, p.id) " +
            "FROM Product p WHERE not p.isApproved")
    List<BasicProduct> findAllNoApproved();

    List<Product> findByIsApprovedTrueAndStockGreaterThan(int stock);

}
