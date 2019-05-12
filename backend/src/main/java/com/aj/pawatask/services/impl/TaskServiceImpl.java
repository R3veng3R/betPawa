package com.aj.pawatask.services.impl;

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

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task delete(Task task) {
        taskRepository.deleteById(task.getId());
        return task;
    }

    @Override
    public Task getById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }
}
