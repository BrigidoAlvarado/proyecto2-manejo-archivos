package org.archivos.ecommercegt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * The type Roletype.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "role")
public class Roletype {
    @Id
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    //TODO [Reverse Engineering] generate columns from DB
}