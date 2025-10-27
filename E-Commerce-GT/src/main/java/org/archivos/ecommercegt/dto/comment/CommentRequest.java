package org.archivos.ecommercegt.dto.comment;

import lombok.Builder;
import lombok.Data;

/**
 * The type Comment request.
 */
@Builder
@Data
public class CommentRequest {
    private final int productId;
    private final String comment;
}
