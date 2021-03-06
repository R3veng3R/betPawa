package com.aj.pawatask.controllers;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.services.impl.TaskServiceImpl;
import com.aj.pawatask.utils.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public Page<Task> getTaskList(@RequestParam int page, @RequestParam int pageSize) {
        return taskService.getTaskList(page, pageSize);
    }

    @PostMapping()
    public void createTask(@RequestBody TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }

}
