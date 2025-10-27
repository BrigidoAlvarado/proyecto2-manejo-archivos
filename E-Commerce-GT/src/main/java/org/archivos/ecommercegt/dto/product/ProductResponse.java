package org.archivos.ecommercegt.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.comment.CommentResponse;

import java.util.List;

/**
 * The type Product response.
 */
@Data
@Builder
@RequiredArgsConstructor
public class ProductResponse {

    private final Integer id;
    private final double price;
    private final double stock;
    private final boolean isNew;
    private final int qualification;
    private final String name;
    private final String description;
    private final String image;
    private final List<String> categories;
    private final List<CommentResponse> comments;
}
