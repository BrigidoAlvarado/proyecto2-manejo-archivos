package org.archivos.ecommercegt.dto.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean isNew;
    private List<String> categories;
    private MultipartFile image;

    public void setIsNew(String isNew) {
        this.isNew = Boolean.parseBoolean(isNew);
    }

    public void setCategories(String categories) {
        this.categories = Arrays.asList(categories.split(","));
    }
}