package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Wallet repository.
 */
public interface WalletRepository extends JpaRepository<Wallet, Integer> {


    /**
     * Find by user optional.
     *
     * @param user the user
     * @return the optional
     */
    Optional<Wallet> findByUser(User user);
}
