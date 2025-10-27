package org.archivos.ecommercegt.dto.card;

import lombok.Builder;
import lombok.Data;

/**
 * The type Pay card request.
 */
@Data
@Builder
public class PayCardRequest {
    private final String cardNumber;
    private final boolean save;
}
