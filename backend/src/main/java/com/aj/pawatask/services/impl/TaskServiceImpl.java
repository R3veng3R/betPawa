package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.repositories.CommentRepository;
import com.aj.pawatask.repositories.TaskRepository;
import com.aj.pawatask.services.TaskService;
import com.aj.pawatask.utils.dto.TaskDTO;
import com.aj.pawatask.utils.dto.mappers.TaskMapper;
import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);
    private TaskRepository taskRepository;
    private CommentRepository commentRepository;
    private UserServiceImpl userService;

    public TaskServiceImpl(TaskRepository taskRepository, CommentRepository commentRepository, UserServiceImpl userService) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    public Page<Task> getTaskList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Task.COLUMN_LIST_SORT).descending());
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task saveTask(TaskDTO taskDTO) {
        User user = userService.getUserById(taskDTO.getUserId());

        Task task = taskMapper.taskDTOtoTask(taskDTO);
        task.setId(taskDTO.getId());
        task.setUser(user);
        taskRepository.save(task);

        if (!StringUtils.isEmpty(taskDTO.getComment())) {
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setTaskId(task.getId());
            comment.setComment(taskDTO.getComment());
            commentRepository.save(comment);
        }

        return task;
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
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

    @Override
    public void saveAll(List<Task> taskList) {
        if(taskList.size() > 0) {
            taskRepository.saveAll(taskList);
        }
    }
}
