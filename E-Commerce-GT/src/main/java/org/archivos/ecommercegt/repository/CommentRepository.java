package org.archivos.ecommercegt.repository;

import org.archivos.ecommercegt.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
