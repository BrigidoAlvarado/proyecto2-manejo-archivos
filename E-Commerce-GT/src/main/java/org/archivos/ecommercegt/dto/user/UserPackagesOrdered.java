package org.archivos.ecommercegt.dto.user;

import lombok.Data;

/**
 * The type User packages ordered.
 */
@Data
public class UserPackagesOrdered {

    private int id;
    private String name;
    private String email;
    private Long packagesOrdered;

    /**
     * Instantiates a new User packages ordered.
     *
     * @param id              the id
     * @param name            the name
     * @param email           the email
     * @param packagesOrdered the packages ordered
     */
    public UserPackagesOrdered(int id, String name, String email, Long packagesOrdered) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.packagesOrdered = packagesOrdered;
    }
}
