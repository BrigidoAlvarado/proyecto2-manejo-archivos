package org.archivos.ecommercegt.dto.card;

import lombok.Builder;
import lombok.Data;

/**
 * The type Credit card response.
 */
@Data
@Builder
public class CreditCardResponse {
    private final String cardNumber;
    private final int id;
}
