package com.aj.pawatask.services;

import com.aj.pawatask.models.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment);
    List<Comment> getTaskComments(Long taskId);
}
