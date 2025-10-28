package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.DeliveryPackage;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Delivery package repository.
 */
public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Integer> {
    /**
     * Find all by is delivered list.
     *
     * @param isDelivered the is delivered
     * @return the list
     */
    List<DeliveryPackage> findAllByIsDelivered(Boolean isDelivered);

    /**
     * Find all by is revised list.
     *
     * @param isRevised the is revised
     * @return the list
     */
    List<DeliveryPackage> findAllByIsRevised(Boolean isRevised);

    /**
     * Find by shopping cart user list.
     *
     * @param shoppingCartUser the shopping cart user
     * @return the list
     */
    List<DeliveryPackage> findByShoppingCartUser(User shoppingCartUser);
}