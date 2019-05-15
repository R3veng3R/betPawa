package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.models.User;
import com.aj.pawatask.repositories.CommentRepository;
import com.aj.pawatask.services.CommentService;
import com.aj.pawatask.services.UserService;
import com.aj.pawatask.utils.dto.CommentDTO;
import com.aj.pawatask.utils.dto.mappers.CommentMapper;
import com.aj.pawatask.utils.dto.mappers.TaskMapper;
import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Log
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    private CommentRepository commentRepository;
    private UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment saveComment(CommentDTO commentDTO) {
        if (commentDTO.getComment() == null) {
            return null;
        }

        User user = userService.getUserById(commentDTO.getUserId());
        Comment comment = commentMapper.commentDTOtoComment(commentDTO);
        comment.setUser(user);

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
