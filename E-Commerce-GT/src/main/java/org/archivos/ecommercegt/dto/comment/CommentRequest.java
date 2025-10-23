package org.archivos.ecommercegt.dto.comment;

import lombok.Builder;

@Builder
public class CommentRequest {
    private final int productId;
    private final String comment;
}
