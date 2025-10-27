package org.archivos.ecommercegt.dto.deliveryPackage;

import lombok.Builder;
import lombok.Data;
import org.archivos.ecommercegt.dto.shoppingCart.ShoppingCartResponse;

import java.time.Instant;

/**
 * The type Delivery package response.
 */
@Data
@Builder
public class DeliveryPackageResponse {

    private int id;
    private String userName;
    private String userEmail;
    private Instant deliveryDate;
    private Instant departureDate;
    private Instant deliverAt;
    private ShoppingCartResponse shoppingCart;
    private boolean isDelivered;
}
