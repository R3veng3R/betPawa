package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.enums.TaskPriority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceImplTest {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private UserServiceImpl userService;


    private Task addTestData() {
        User user = new User();
        user.setLastName("TestUserLastName");
        user.setName("TestUserName");
        userService.saveUser(user);

        Task task = new Task();
        task.setTitle("Test object 1");
        task.setPriority(TaskPriority.LOW);
        task.setDescription("Test obj description 2");
        task.setDueDate(new Date());
        task.setCreateDate(new Timestamp(task.getDueDate().getTime()));
        task.setUser(user);
        task.setComments(Collections.emptyList());
        taskService.saveTask(task);

        return task;
    }

    @Test
    public void saveTaskTest() {
        addTestData();
        List<Task> taskList = taskService.getTaskList();

        int actual = taskList.size();
        int expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    public void deleteTest() {
        Task taskAdded = addTestData();
        taskService.delete(taskAdded);
        Task actual = taskService.getById(taskAdded.getId());
        assertNull(actual);
    }
}