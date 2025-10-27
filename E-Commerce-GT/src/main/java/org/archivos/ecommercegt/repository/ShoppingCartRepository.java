package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Shopping cart repository.
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    /**
     * Find by status and user optional.
     *
     * @param status the status
     * @param user   the user
     * @return the optional
     */
    Optional<ShoppingCart> findByStatusAndUser(boolean status, User user);
}
