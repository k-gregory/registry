package io.github.k_gregory.registry.controller;


import io.github.k_gregory.registry.model.Comment;
import io.github.k_gregory.registry.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final CommentRepository comments;

    @Autowired
    public IndexController(CommentRepository comments) {
        this.comments = comments;
    }

    @RequestMapping("/")
    @ResponseBody
    Iterable<Comment> index(){
        Comment comment = new Comment();
        comment.value = "Ololo";
        comments.save(comment);

        return comments.findAll();
    }
}
