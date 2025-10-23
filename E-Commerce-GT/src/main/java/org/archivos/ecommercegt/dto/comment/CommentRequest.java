package org.archivos.ecommercegt.dto.comment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentRequest {
    private final int productId;
    private final String comment;
}
