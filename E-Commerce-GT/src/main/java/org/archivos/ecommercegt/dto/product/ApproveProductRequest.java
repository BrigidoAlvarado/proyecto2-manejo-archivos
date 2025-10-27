package org.archivos.ecommercegt.dto.product;

import lombok.Data;

/**
 * The type Approve product request.
 */
@Data
public class ApproveProductRequest {
    private int id;
    private Boolean isApprove;
}
