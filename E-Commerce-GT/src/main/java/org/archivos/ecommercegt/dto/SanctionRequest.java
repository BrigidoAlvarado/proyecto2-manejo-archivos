package org.archivos.ecommercegt.dto;

import lombok.Builder;
import lombok.Data;
import org.archivos.ecommercegt.dto.product.ApproveProductRequest;

@Data
@Builder
public class SanctionRequest {

    private final int productId;
    private final String reason;
    private final int amountDays;
    private final ApproveProductRequest approveProductRequest;
}
