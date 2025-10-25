package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.Optional;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer> {
    Optional<PurchaseDetail> findByProductId(Integer productId);

    Optional<PurchaseDetail> findByProductAndShoppingCart(Product product, ShoppingCart shoppingCart);
}
