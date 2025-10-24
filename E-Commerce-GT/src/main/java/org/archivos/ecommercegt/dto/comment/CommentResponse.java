package org.archivos.ecommercegt.dto.comment;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CommentResponse {
    private final String userName;
    private final String comment;
    private Instant date;

    public CommentResponse(String userName, String comment, Instant date) {
        this.userName = userName;
        this.comment = comment;
        this.date = date;
    }
}
