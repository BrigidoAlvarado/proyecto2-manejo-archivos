package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
