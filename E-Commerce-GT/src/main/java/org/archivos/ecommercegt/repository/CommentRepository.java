package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.dto.comment.CommentResponse;
import org.archivos.ecommercegt.models.Comment;
import org.archivos.ecommercegt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Comment repository.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * Find by product list.
     *
     * @param product the product
     * @return the list
     */
    List<Comment> findByProduct(Product product);


}
