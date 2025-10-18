package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {


    Optional<Wallet> findByUser(User user);
}
