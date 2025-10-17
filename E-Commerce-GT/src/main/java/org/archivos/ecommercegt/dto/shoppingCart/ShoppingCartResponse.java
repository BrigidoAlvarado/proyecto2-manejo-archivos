package org.archivos.ecommercegt.dto.shoppingCart;

import lombok.Builder;
import lombok.Data;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailResponse;

import java.util.List;

@Data
@Builder
public class ShoppingCartResponse {

    private final int id;
    private final List<PurchaseDetailResponse> purchaseDetails;
}
