package org.archivos.ecommercegt.dto.purchaseDetail;

import lombok.Data;

/**
 * The type Purchase detail request.
 */
@Data
public class PurchaseDetailRequest {
    private Integer purchaseDetailId;
    private int productId;
    private int amount;
}
