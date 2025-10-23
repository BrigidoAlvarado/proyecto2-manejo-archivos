package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.comment.CommentRequest;
import org.archivos.ecommercegt.models.Comment;
import org.archivos.ecommercegt.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    public Comment save(CommentRequest comment) {
        return commentRepository.save(comment);
    }
}
