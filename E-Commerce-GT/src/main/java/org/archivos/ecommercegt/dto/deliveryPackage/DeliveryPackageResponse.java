package org.archivos.ecommercegt.dto.deliveryPackage;

import lombok.Builder;
import lombok.Data;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;

import java.time.Instant;

@Data
@Builder
public class DeliveryPackageResponse {

    private int id;
    private String userName;
    private Instant deliveryDate;
    private Instant departureDate;
    private ShoppingCartResponse shoppingCart;
    private boolean isDelivered;
}
