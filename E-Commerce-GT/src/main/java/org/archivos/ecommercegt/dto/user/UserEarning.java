package org.archivos.ecommercegt.dto.user;

import lombok.Data;

@Data
public class UserEarning {

    private final String name;
    private final String email;
    private final Double earning;

    public UserEarning(String username, String email, Double earning) {
        this.name = username;
        this.email = email;
        this.earning = earning;
    }
}
