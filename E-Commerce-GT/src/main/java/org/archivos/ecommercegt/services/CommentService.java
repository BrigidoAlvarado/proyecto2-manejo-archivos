package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.comment.CommentRequest;
import org.archivos.ecommercegt.models.Comment;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;
    private final ProductService productService;

    public Comment save(CommentRequest commentRequest) {

        final User user = userService.getUser();
        final Product product = productService.getProductById(commentRequest.getProductId());

        Comment comment = new Comment();
        comment.setContent( commentRequest.getComment() );
        comment.setUser( user );
        comment.setCreatedAt( Instant.now() );
        comment.setProduct( product );

        return commentRepository.save(comment);
    }
}
