package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.Qualification;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Qualification repository.
 */
public interface QualificationRepository extends JpaRepository<Qualification, Integer> {
    /**
     * Find by user and product qualification.
     *
     * @param user    the user
     * @param product the product
     * @return the qualification
     */
    Qualification findByUserAndProduct(User user, Product product);

    /**
     * Find by product list.
     *
     * @param product the product
     * @return the list
     */
    List<Qualification> findByProduct(Product product);
}
