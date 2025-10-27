package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.Optional;

/**
 * The interface Purchase detail repository.
 */
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer> {
    /**
     * Find by product id optional.
     *
     * @param productId the product id
     * @return the optional
     */
    Optional<PurchaseDetail> findByProductId(Integer productId);

    /**
     * Find by product and shopping cart optional.
     *
     * @param product      the product
     * @param shoppingCart the shopping cart
     * @return the optional
     */
    Optional<PurchaseDetail> findByProductAndShoppingCart(Product product, ShoppingCart shoppingCart);
}
