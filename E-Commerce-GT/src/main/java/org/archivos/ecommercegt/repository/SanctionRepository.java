package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Sanction;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * The interface Sanction repository.
 */
public interface SanctionRepository extends JpaRepository<Sanction, Integer> {

    /**
     * Find by user and end at greater than optional.
     *
     * @param user               the user
     * @param endAtIsGreaterThan the end at is greater than
     * @return the optional
     */
    Optional<Sanction> findByUserAndEndAtGreaterThan(User user, Instant endAtIsGreaterThan);

    /**
     * Find by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Sanction> findByUserId(int userId);
}
