package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.comment.CommentResponse;
import org.archivos.ecommercegt.dto.product.BasicProduct;
import org.archivos.ecommercegt.dto.product.MoreSellingProduct;
import org.archivos.ecommercegt.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new org.archivos.ecommercegt.dto.product.BasicProduct(p.name, p.user.name, p.id) " +
            "FROM Product p WHERE not p.isApproved")
    List<BasicProduct> findAllNoApproved();

    List<Product> findByIsRevisedTrueAndIsApprovedTrueAndStockGreaterThan(int stock);

    @Query("""
        select new org.archivos.ecommercegt.dto.product.MoreSellingProduct(
                p.id,
                p.name,
                p.user.name,
                p.user.email,
                p.price,
                sum( pd.amount)
            )
            from Product p
                join PurchaseDetail pd on p.id = pd.product.id
                join ShoppingCart crt  on pd.shoppingCart.id = crt.id
                join DeliveryPackage pck on crt.id = pck.shoppingCart.id
            where ( cast(:startDate as timestamp ) is null or :startDate < pck.departureDate )
              and ( cast(:endDate as timestamp ) is null or pck.departureDate < :endDate )
            group by p.id, p.name, p.user.name, p.user.email, p.price
            order by sum(pd.amount) desc
    """)
    List<MoreSellingProduct> getMoreSellingProducts(Instant startDate, Instant endDate, Pageable pageable);

    List<Product> findByIsRevisedTrueAndIsApprovedFalse();
}