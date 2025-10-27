package org.archivos.ecommercegt.dto.user;

import lombok.Data;

/**
 * The type User earning.
 */
@Data
public class UserEarning {

    private final String name;
    private final String email;
    private final Double earning;

    /**
     * Instantiates a new User earning.
     *
     * @param username the username
     * @param email    the email
     * @param earning  the earning
     */
    public UserEarning(String username, String email, Double earning) {
        this.name = username;
        this.email = email;
        this.earning = earning;
    }
}
