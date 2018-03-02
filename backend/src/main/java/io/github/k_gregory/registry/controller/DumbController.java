package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.model.Comment;
import io.github.k_gregory.registry.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/dumb")
public class DumbController {
    private final JdbcTemplate jdbc;
    private final CommentRepository commentRepository;

    @Autowired
    public DumbController(JdbcTemplate jdbc, CommentRepository commentRepository) {
        this.jdbc = jdbc;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/simplest")
    public String dumb() {
        return "dumb";
    }

    @GetMapping("/json")
    public Comment json() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setMessage("Hi!");
        return comment;
    }

    @GetMapping("/db-single-template")
    public Comment dbSingleTemplate() {
        return jdbc.query("select c.id, c.message from comment c", (rs) -> {
            rs.next();
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setMessage(rs.getString("message"));
            return comment;
        });
    }

    @GetMapping("/db-single-springdata")
    public Comment dbSingleSpringData() {
        return commentRepository.findOne();
    }

    @GetMapping("/db-all-springdata")
    public List<Comment> dbAllSpringData() {
        return commentRepository.findAll();
    }
}
