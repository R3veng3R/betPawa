package com.aj.pawatask.controllers;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.services.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
