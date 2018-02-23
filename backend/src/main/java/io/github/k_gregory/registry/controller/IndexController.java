package io.github.k_gregory.registry.controller;


import io.github.k_gregory.registry.model.Comment;
import io.github.k_gregory.registry.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

class CommentDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setMessage(message);
        return comment;
    }
}

@Controller
public class IndexController {
    private final CommentRepository comments;

    @Autowired
    public IndexController(CommentRepository comments) {
        this.comments = comments;
    }

    @ModelAttribute("comments")
    public Iterable<Comment> modelComments() {
        return comments.findAll();
    }

    @GetMapping("/dumb")
    @ResponseBody
    public String dumb() {
        return "dumb";
    }

    @PostMapping("/")
    public String addComment(CommentDTO comment) {
        comments.save(comment.toComment());
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("comment", new CommentDTO());
        return "index";
    }
}
