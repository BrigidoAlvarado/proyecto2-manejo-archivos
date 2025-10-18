package org.archivos.ecommercegt.dto.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayCardRequest {
    private final String cardNumber;
    private final boolean save;
}
