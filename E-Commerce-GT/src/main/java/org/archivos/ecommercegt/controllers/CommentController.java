package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.comment.CommentRequest;
import org.archivos.ecommercegt.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Comment controller.
 */
@RestController
@RequestMapping( ApplicationConfig.BASE_URL + "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Add comment response entity.
     *
     * @param comment the comment
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<?> addComment(
            @RequestBody CommentRequest comment) {
        commentService.save(comment);
        return ResponseEntity.ok().build();
    }
}
