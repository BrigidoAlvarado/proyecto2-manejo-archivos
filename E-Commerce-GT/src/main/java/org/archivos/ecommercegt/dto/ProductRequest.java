package org.archivos.ecommercegt.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean isNew;
    private MultipartFile image;

    public void setIsNew(String isNew) {
        this.isNew = Boolean.parseBoolean(isNew);
    }
}