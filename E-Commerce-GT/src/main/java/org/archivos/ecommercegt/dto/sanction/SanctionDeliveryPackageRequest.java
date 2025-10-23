package org.archivos.ecommercegt.dto.sanction;

import lombok.Builder;
import lombok.Data;
import org.archivos.ecommercegt.dto.product.ApproveProductRequest;

@Data
@Builder
public class SanctionDeliveryPackageRequest {

    private final String reason;
    private final int amountDays;
    private final int deliveryPackageId;
}
