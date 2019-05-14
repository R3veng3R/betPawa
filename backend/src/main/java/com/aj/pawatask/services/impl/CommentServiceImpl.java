package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.repositories.CommentRepository;
import com.aj.pawatask.services.CommentService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Log
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (comment == null) {
            return null;
        }

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getTaskComments(Long taskId) {
        if (taskId == null) {
            return Collections.emptyList();
        }

        return commentRepository.findByTaskIdOrderByCreateDateAsc(taskId);
    }


}
