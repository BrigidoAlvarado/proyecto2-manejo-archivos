package org.archivos.ecommercegt.services.utilities;

import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailResponse;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParseService {

    public ShoppingCartResponse parseShoppingCartResponse(ShoppingCart cart){
        final List<PurchaseDetailResponse> purchaseDetailResponses = new ArrayList<>();

        double total = 0;
        for(PurchaseDetail purchaseDetail : cart.getPurchaseDetails()){

            double subTotal =  purchaseDetail.getProduct().getPrice() * purchaseDetail.getAmount();
            total = total + subTotal;

            purchaseDetailResponses.add(
                    PurchaseDetailResponse.builder()
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
