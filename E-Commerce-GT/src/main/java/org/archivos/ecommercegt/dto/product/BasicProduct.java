package org.archivos.ecommercegt.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The type Basic product.
 */
@Data
@Builder
@RequiredArgsConstructor
public class BasicProduct {

    private final Integer id;
    private final String name;
    private final String user;
    private final Boolean isApproved;

    /**
     * Instantiates a new Basic product.
     *
     * @param name       the name
     * @param user       the user
     * @param id         the id
     * @param isApproved the is approved
     */
    public BasicProduct(String name, String user, Integer id, Boolean isApproved) {
        this.name = name;
        this.user = user;
        this.id = id;
        this.isApproved = isApproved;
    }
}
