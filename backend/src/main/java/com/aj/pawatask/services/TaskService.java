package com.aj.pawatask.services;

import com.aj.pawatask.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTaskList();
    Task saveTask(Task task);
}
