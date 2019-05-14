package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.models.Task;
import com.aj.pawatask.repositories.TaskRepository;
import com.aj.pawatask.services.TaskService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Log
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private CommentServiceImpl commentService;

    public TaskServiceImpl(TaskRepository taskRepository, CommentServiceImpl commentService) {
        this.taskRepository = taskRepository;
        this.commentService = commentService;
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        Task savedTask = taskRepository.save(task);

        if (task.getComments().size() > 0) {
            Comment comment = task.getComments().get(0);
            comment.setTaskId(savedTask.getId());
            commentService.saveComment(comment);
        }

        return savedTask;
    }

    @Override
    public Task delete(Task task) {
        taskRepository.deleteById(task.getId());
        return task;
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }
}
