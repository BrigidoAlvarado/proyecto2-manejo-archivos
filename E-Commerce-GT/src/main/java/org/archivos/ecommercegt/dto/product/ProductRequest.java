package org.archivos.ecommercegt.dto.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * The type Product request.
 */
@Data
public class ProductRequest {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean isNew;
    private List<String> categories;
    private MultipartFile image;

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(String id){
        this.id = Integer.parseInt(id);
    }

    /**
     * Sets is new.
     *
     * @param isNew the is new
     */
    public void setIsNew(String isNew) {
        this.isNew = Boolean.parseBoolean(isNew);
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     */
    public void setCategories(String categories) {
        this.categories = Arrays.asList(categories.split(","));
    }
}