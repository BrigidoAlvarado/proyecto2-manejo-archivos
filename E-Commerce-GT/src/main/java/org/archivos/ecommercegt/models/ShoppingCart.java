package org.archivos.ecommercegt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("TRUE")
    @Column(name = "status", nullable = false)
    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "_user", nullable = false, referencedColumnName = "email")
    private User user;

    @OneToMany( mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PurchaseDetail> purchaseDetails;
}