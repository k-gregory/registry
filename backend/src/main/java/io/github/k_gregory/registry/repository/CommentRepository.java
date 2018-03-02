package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comment limit 1", nativeQuery = true)
    Comment findOne();
}
