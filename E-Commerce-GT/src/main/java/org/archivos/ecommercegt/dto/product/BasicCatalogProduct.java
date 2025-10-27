package org.archivos.ecommercegt.dto.product;

import lombok.Builder;
import lombok.Data;

/**
 * The type Basic catalog product.
 */
@Data
@Builder
public class BasicCatalogProduct {

    private Integer id;
    private String name;
    private String image;
    private double price;
    private int stars;

}
