package org.archivos.ecommercegt.dto.comment;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * The type Comment response.
 */
@Data
@Builder
public class CommentResponse {
    private final String userName;
    private final String comment;
    private Instant date;

    /**
     * Instantiates a new Comment response.
     *
     * @param userName the user name
     * @param comment  the comment
     * @param date     the date
     */
    public CommentResponse(String userName, String comment, Instant date) {
        this.userName = userName;
        this.comment = comment;
        this.date = date;
    }
}
