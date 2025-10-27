package org.archivos.ecommercegt.dto.qualification;

import lombok.Builder;
import lombok.Data;

/**
 * The type Qualification request.
 */
@Data
@Builder
public class QualificationRequest {
    private int productId;
    private int starts;
}