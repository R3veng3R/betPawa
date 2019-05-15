package com.aj.pawatask.services;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.utils.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentDTO comment);
    List<Comment> getTaskComments(Long taskId);
}
