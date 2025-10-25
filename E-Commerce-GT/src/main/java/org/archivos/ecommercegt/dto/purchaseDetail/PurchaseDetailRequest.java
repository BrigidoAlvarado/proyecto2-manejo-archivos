package org.archivos.ecommercegt.dto.purchaseDetail;

import lombok.Data;

@Data
public class PurchaseDetailRequest {
    private Integer purchaseDetailId;
    private int productId;
    private int amount;
}
