package org.archivos.ecommercegt.dto.product;

import lombok.Data;

/**
 * The type More selling product.
 */
@Data
public class MoreSellingProduct {
    final private Integer id;
    final private String name;
    final private String userName;
    final private String userEmail;
    final private Double price;
    final private Long amount;

    /**
     * Instantiates a new More selling product.
     *
     * @param id        the id
     * @param name      the name
     * @param userName  the user name
     * @param userEmail the user email
     * @param price     the price
     * @param amount    the amount
     */
    public MoreSellingProduct(Integer id, String name, String userName, String userEmail, Double price, Long amount) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.userName = userName;
        this.price = price;
        this.amount = amount;
    }
}
