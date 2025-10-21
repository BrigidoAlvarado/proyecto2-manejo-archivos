package org.archivos.ecommercegt.dto.user;

import lombok.Data;

@Data
public class UserProductsApprove {

    private final int id;
    private final String name;
    private final String email;
    private final Long productsApproved;

    public UserProductsApprove(int id, String name, String email, Long productsApproved) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.productsApproved = productsApproved;
    }
}
