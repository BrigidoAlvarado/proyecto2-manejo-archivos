package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Transaction repository.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
