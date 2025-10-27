package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.user.UserEarning;
import org.archivos.ecommercegt.dto.user.UserPackagesOrdered;
import org.archivos.ecommercegt.dto.user.UserProductsApprove;
import org.archivos.ecommercegt.dto.user.UserProductsSend;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by top spent list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param pageable  the pageable
     * @return the list
     */
    @Query("""
           select new org.archivos.ecommercegt.dto.user.UserEarning(
                      wll.user.name,
                      wll.user.email,
                      sum (tr.amount)
                      )
           from Transaction tr
           join Wallet wll on tr.wallet.id = wll.id
           where ( cast(:startDate as timestamp ) is null or :startDate < tr.date )
             and ( cast(:endDate as timestamp ) is null or tr.date < :endDate )
           group by wll.user.name, wll.user.email
           order by sum ( tr.amount ) desc
           """)
    List<UserEarning> findUserByTopSpent(Instant startDate, Instant endDate, Pageable pageable);

    /**
     * Find user by products send list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param pageable  the pageable
     * @return the list
     */
    @Query("""
    select new org.archivos.ecommercegt.dto.user.UserProductsSend(
        prod.user.id,
        prod.user.name,
        prod.user.email,
        sum ( pd.amount )
        )
    from DeliveryPackage pck
        join ShoppingCart crt on pck.shoppingCart.id = crt.id
        join PurchaseDetail pd on pd.shoppingCart.id = crt.id
        join Product prod on pd.product.id = prod.id
    where ( cast(:startDate as timestamp ) is null or :startDate < pck.departureDate )
    and ( cast(:endDate as timestamp ) is null or pck.departureDate < :endDate )
    group by prod.user.id, prod.user.name
    order by sum( pd.amount ) desc
    """)
    List<UserProductsSend> findUserByProductsSend(Instant startDate, Instant endDate, Pageable pageable);

    /**
     * Find user by packages ordered list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param pageable  the pageable
     * @return the list
     */
    @Query("""
    select new org.archivos.ecommercegt.dto.user.UserPackagesOrdered(
        crt.user.id,
        crt.user.name,
        crt.user.email,
        count ( crt.user.id )
        )
    from DeliveryPackage pck
    join ShoppingCart crt on crt.id = pck.shoppingCart.id
    where ( cast(:startDate as timestamp ) is null or :startDate < pck.departureDate )
    and ( cast(:endDate as timestamp ) is null or pck.departureDate < :endDate )
    group by crt.user.id, crt.user.name, crt.user.email
    order by sum ( crt.user.id ) desc
    """)
    List<UserPackagesOrdered> findUserByPackagesOrdered(Instant startDate, Instant endDate, Pageable pageable);

    /**
     * Find users by a proved products list.
     *
     * @param pageable the pageable
     * @return the list
     */
    @Query("""
    select new org.archivos.ecommercegt.dto.user.UserProductsApprove(
        u.id,
        u.name,
        u.email,
        count ( u.id )
        )
    from User u
    join Product pd on u.email = pd.user.email
    where ( pd.isApproved = true  )
    group by u.id, u.name, u.email
    order by count ( u.id ) desc
    """)
    List<UserProductsApprove> findUsersByAProvedProducts(Pageable pageable);
}