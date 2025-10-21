package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.user.UserEarning;
import org.archivos.ecommercegt.dto.user.UserProductsSend;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

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
    """)
    List<UserProductsSend> findUserByProductsSend(Instant startDate, Instant endDate, Pageable pageable);
}