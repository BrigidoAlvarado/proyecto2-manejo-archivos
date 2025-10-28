package org.archivos.ecommercegt.dto.shoppingCart;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailResponse;

import java.util.List;

/**
 * The type Shopping cart response.
 */
@Data
@Builder
@ToString
public class ShoppingCartResponse {

    private final int id;
    private final double total;
    private final List<PurchaseDetailResponse> purchaseDetails;
}
