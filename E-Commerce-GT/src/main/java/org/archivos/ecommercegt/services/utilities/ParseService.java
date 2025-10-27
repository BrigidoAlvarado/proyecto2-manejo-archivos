package org.archivos.ecommercegt.services.utilities;

import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailResponse;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Parse service.
 */
@Service
public class ParseService {

    /**
     * Parse shopping cart response shopping cart response.
     *
     * @param cart the cart
     * @return the shopping cart response
     */
    public ShoppingCartResponse parseShoppingCartResponse(ShoppingCart cart){
        final List<PurchaseDetailResponse> purchaseDetailResponses = new ArrayList<>();

        double total = 0;
        for(PurchaseDetail purchaseDetail : cart.getPurchaseDetails()){

            double subTotal =  purchaseDetail.getProduct().getPrice() * purchaseDetail.getAmount();
            total = total + subTotal;

            purchaseDetailResponses.add(
                    PurchaseDetailResponse.builder()
                            .purchaseDetailId(purchaseDetail.getId())
                            .price( purchaseDetail.getProduct().getPrice() )
                            .amount( purchaseDetail.getAmount() )
                            .productId( purchaseDetail.getProduct().getId() )
                            .name( purchaseDetail.getProduct().getName() )
                            .subTotal( subTotal )
                            .build()
            );
        }

        return ShoppingCartResponse.builder()
                .id( cart.getId() )
                .purchaseDetails( purchaseDetailResponses )
                .total(total)
                .build();
    }

}
