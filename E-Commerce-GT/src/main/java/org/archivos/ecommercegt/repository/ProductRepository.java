package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.product.BasicProduct;
import org.archivos.ecommercegt.dto.product.MoreSellingProduct;
import org.archivos.ecommercegt.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new org.archivos.ecommercegt.dto.product.BasicProduct(p.name, p.user.name, p.id) " +
            "FROM Product p WHERE not p.isApproved")
    List<BasicProduct> findAllNoApproved();

    List<Product> findByIsApprovedTrueAndStockGreaterThan(int stock);

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
            where crt.status = false
            group by p.id, p.name, p.user.name, p.user.email, p.price
            order by sum(pd.amount) desc
    """)
    List<MoreSellingProduct> getMoreSellingProducts(Pageable page);
}