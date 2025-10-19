package org.archivos.ecommercegt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    public Product() {}

    public Product(Integer id, String name, User user) {
        this.id = id;
        this.name = name;
       this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "is_new", nullable = false)
    private Boolean isNew;

    @ColumnDefault("false")
    @Column(name = "is_approved")
    private boolean isApproved;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product"),
            inverseJoinColumns = @JoinColumn( name = "category")
    )
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "_user", nullable = false, referencedColumnName = "email")
    private User user;

}