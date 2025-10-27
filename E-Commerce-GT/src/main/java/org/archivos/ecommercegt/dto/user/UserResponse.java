package org.archivos.ecommercegt.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * The type User response.
 */
@Builder
@Data
public class UserResponse {

    private final Integer id;
    private final String username;
    private final String email;
    private final String role;
    private final boolean enabled;

}
