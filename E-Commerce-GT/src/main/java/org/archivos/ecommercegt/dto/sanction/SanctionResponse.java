package org.archivos.ecommercegt.dto.sanction;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * The type Sanction response.
 */
@Data
@Builder
public class SanctionResponse {
    private final String reason;
    private final Instant createdAt;
    private final Instant endAt;
}
