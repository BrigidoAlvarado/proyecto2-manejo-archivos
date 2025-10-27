package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Notification;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Notification repository.
 */
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    /**
     * Find by user list.
     *
     * @param user the user
     * @return the list
     */
    List<Notification> findByUser(User user);
}
