package org.archivos.ecommercegt.dto.user;

import lombok.Data;

/**
 * The type User products send.
 */
@Data
public class UserProductsSend {
    private final int id;
    private final String name;
    private final String email;
    private final Long productsSend;
}
