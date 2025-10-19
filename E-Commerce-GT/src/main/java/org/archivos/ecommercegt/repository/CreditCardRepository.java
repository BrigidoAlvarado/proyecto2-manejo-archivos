package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.CreditCard;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    List<CreditCard> findAllByUser(User user);
}
