package org.archivos.ecommercegt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicCatalogProduct {

    private Integer id;
    private String name;
    private String image;
    private double price;
    private int stars;

}
