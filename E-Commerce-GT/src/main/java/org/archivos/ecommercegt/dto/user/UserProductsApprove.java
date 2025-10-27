package org.archivos.ecommercegt.dto.user;

import lombok.Data;

/**
 * The type User products approve.
 */
@Data
public class UserProductsApprove {

    private final int id;
    private final String name;
    private final String email;
    private final Long productsApproved;

    /**
     * Instantiates a new User products approve.
     *
     * @param id               the id
     * @param name             the name
     * @param email            the email
     * @param productsApproved the products approved
     */
    public UserProductsApprove(int id, String name, String email, Long productsApproved) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.productsApproved = productsApproved;
    }
}
