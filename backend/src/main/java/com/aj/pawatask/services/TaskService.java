package com.aj.pawatask.services;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.utils.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<Task> getTaskList();
    Task saveTask(TaskDTO task);
    Task delete(Task task);
    void deleteById(Long id);
    Task getById(Long id);
}
