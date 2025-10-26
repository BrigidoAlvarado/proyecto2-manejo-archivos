package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Notification;
import org.archivos.ecommercegt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUser(User user);
}
