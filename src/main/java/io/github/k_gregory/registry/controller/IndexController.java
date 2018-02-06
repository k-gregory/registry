package io.github.k_gregory.registry.controller;


import io.github.k_gregory.registry.model.Comment;
import io.github.k_gregory.registry.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    private final CommentRepository comments;

    @Autowired
    public IndexController(CommentRepository comments) {
        this.comments = comments;
    }

    @ModelAttribute("comments")
    Iterable<Comment> modelComments(){
        return comments.findAll();
    }

    @PostMapping("/")
    String addComment(Model model, Comment comment){
        comments.save(comment);
        return "redirect:/";
    }

    @GetMapping("/")
    String index(Model model){
        model.addAttribute("comment", new Comment());
        return "index";
    }
}
