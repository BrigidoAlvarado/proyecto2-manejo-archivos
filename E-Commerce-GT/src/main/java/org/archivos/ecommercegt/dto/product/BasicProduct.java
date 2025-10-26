package org.archivos.ecommercegt.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BasicProduct {

    private final Integer id;
    private final String name;
    private final String user;
    private final Boolean isApproved;

    public BasicProduct(String name, String user, Integer id, Boolean isApproved) {
        this.name = name;
        this.user = user;
        this.id = id;
        this.isApproved = isApproved;
    }
}
