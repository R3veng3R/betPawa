package com.aj.pawatask.controllers;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.services.impl.TaskServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public List<Task> getTaskList() {
        return taskService.getTaskList();
    }


    @PostMapping()
    public void createTask(@RequestBody Task task) {
        taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }

}
