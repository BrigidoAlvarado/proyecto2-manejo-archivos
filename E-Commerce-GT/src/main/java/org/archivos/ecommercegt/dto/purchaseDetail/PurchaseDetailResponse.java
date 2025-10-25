package org.archivos.ecommercegt.dto.purchaseDetail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDetailResponse {
    private final Integer purchaseDetailId;
    private final int productId;
    private final int amount;
    private final double price;
    private final double subTotal;
    private final String name;
}
