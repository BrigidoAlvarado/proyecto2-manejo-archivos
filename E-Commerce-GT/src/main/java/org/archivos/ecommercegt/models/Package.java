package org.archivos.ecommercegt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "is_delivered", nullable = false)
    private Boolean isDelivered = false;

    @Column(name = "departure_date", nullable = false)
    private Instant departureDate;

    @Column(name = "delivery_date", nullable = false)
    private Instant deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "shopping_cart", nullable = false)
    private ShoppingCart shoppingCart;

}