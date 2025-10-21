package org.archivos.ecommercegt.dto.user;

import lombok.Data;

@Data
public class UserPackagesOrdered {

    private int id;
    private String name;
    private String email;
    private Long packagesOrdered;

    public UserPackagesOrdered(int id, String name, String email, Long packagesOrdered) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.packagesOrdered = packagesOrdered;
    }
}
