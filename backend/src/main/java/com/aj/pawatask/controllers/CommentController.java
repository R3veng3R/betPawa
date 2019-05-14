package com.aj.pawatask.controllers;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.services.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public List<Comment> getTaskComments(@PathVariable Long id) {
        return commentService.getTaskComments(id);
    }

    @PostMapping("")
    public void addComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
    }
}
