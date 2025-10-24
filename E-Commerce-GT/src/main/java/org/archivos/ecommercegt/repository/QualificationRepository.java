package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.Qualification;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QualificationRepository extends JpaRepository<Qualification, Integer> {
    Qualification findByUserAndProduct(User user, Product product);

    List<Qualification> findByProduct(Product product);
}
