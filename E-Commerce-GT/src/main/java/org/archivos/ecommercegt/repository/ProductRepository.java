package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.comment.CommentResponse;
import org.archivos.ecommercegt.dto.product.BasicProduct;
import org.archivos.ecommercegt.dto.product.MoreSellingProduct;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * The interface Product repository.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Find all no approved list.
     *
     * @return the list
     */
    @Query("SELECT new org.archivos.ecommercegt.dto.product.BasicProduct(p.name, p.user.name, p.id, p.isApproved) " +
            "FROM Product p WHERE not p.isApproved")
    List<BasicProduct> findAllNoApproved();

    /**
     * Find by is revised true and is approved true and stock greater than list.
     *
     * @param stock the stock
     * @return the list
     */
    List<Product> findByIsRevisedTrueAndIsApprovedTrueAndStockGreaterThan(int stock);

    /**
     * Gets more selling products.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param pageable  the pageable
     * @return the more selling products
     */
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

    /**
     * Find by is revised true and is approved false list.
     *
     * @return the list
     */
    List<Product> findByIsRevisedTrueAndIsApprovedFalse();

    /**
     * Find by user email list.
     *
     * @param userEmail the user email
     * @return the list
     */
    List<Product> findByUserEmail(String userEmail);

    /**
     * Find by user list.
     *
     * @param user the user
     * @return the list
     */
    List<Product> findByUser(User user);

    /**
     * Find by is revised false list.
     *
     * @return the list
     */
    List<Product> findByIsRevisedFalse();
}