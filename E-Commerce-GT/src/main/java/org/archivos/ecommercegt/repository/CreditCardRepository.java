package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.CreditCard;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Credit card repository.
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    /**
     * Find all by user list.
     *
     * @param user the user
     * @return the list
     */
    List<CreditCard> findAllByUser(User user);
}
