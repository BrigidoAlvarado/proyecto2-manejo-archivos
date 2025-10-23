package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Integer> {
    List<DeliveryPackage> findAllByIsDelivered(Boolean isDelivered);

    List<DeliveryPackage> findAllByIsRevised(Boolean isRevised);
}