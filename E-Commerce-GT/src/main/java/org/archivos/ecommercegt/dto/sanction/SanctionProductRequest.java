package org.archivos.ecommercegt.dto.sanction;

import lombok.Builder;
import lombok.Data;
import org.archivos.ecommercegt.dto.product.ApproveProductRequest;

@Data
@Builder
public class SanctionProductRequest {

    private final int productId;
    private final String reason;
    private final int amountDays;
    private final ApproveProductRequest approveProductRequest;
}
