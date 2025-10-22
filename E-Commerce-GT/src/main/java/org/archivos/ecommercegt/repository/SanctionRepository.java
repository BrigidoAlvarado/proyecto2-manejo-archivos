package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Sanction;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface SanctionRepository extends JpaRepository<Sanction, Integer> {

    Optional<Sanction> findByUserAndEndAtGreaterThan(User user, Instant endAtIsGreaterThan);
}
