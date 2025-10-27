package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.comment.CommentRequest;
import org.archivos.ecommercegt.dto.comment.CommentResponse;
import org.archivos.ecommercegt.models.Comment;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.CommentRepository;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Comment service.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    private final UserService userService;

    /**
     * Save comment.
     *
     * @param commentRequest the comment request
     * @return the comment
     */
    public Comment save(CommentRequest commentRequest) {

        final User user = userService.getUser();
        final Product product = this.getProductById(commentRequest.getProductId());

        Comment comment = new Comment();
        comment.setContent( commentRequest.getComment() );
        comment.setUser( user );
        comment.setCreatedAt( Instant.now() );
        comment.setProduct( product );

        return commentRepository.save(comment);
    }

    /**
     * Gets all comments by product id.
     *
     * @param productId the product id
     * @return the all comments by product id
     */
    public List<CommentResponse> getAllCommentsByProductId(int productId) {
        final Product product = getProductById(productId);
        final List<Comment> comments = commentRepository.findByProduct(product);
        final List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            commentResponses.add(
                    CommentResponse.builder()
                            .date( comment.getCreatedAt() )
                            .comment( comment.getContent() )
                            .userName( comment.getUser().getUsername() ).build()
            );
        }
        return commentResponses;
    }

    private Product getProductById(int id) {
        return  productRepository.findById( id )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no econtrado"));

    }
}
