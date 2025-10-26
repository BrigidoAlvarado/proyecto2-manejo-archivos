package org.archivos.ecommercegt.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    private final int id;
    private final String name;
    private final String email;
    private final String role;
    private final String password;
}
