package com.aj.pawatask.services;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.utils.dto.TaskDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    List<Task> getTaskList();
    Page<Task> getTaskList(int page, int size);
    Task saveTask(TaskDTO task);
    Task saveTask(Task task);
    void deleteById(Long id);
    Task getById(Long id);
    void saveAll(List<Task> taskList);
}
