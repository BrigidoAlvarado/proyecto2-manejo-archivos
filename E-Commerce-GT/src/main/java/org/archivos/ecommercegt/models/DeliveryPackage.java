package org.archivos.ecommercegt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "package")
public class DeliveryPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "is_delivered", nullable = false)
    private Boolean isDelivered = false;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "departure_date", nullable = false)
    private Instant departureDate = Instant.now();

    @ColumnDefault("CURRENT_TIMESTAMP + INTERVAL '5 days'")
    @Column(name = "delivery_date", nullable = false)
    private Instant deliveryDate  = Instant.now().plus(5, ChronoUnit.DAYS);

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "shopping_cart", nullable = false)
    private ShoppingCart shoppingCart;

}