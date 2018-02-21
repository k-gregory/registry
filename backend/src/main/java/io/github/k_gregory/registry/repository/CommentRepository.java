package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
