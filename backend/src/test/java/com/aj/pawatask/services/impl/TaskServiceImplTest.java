package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.services.UserService;
import com.aj.pawatask.utils.enums.TaskPriority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceImplTest {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private UserService userService;


    private void addTestData() {
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
        taskService.saveTask(task);
    }

    @Test
    public void saveTaskTest() {
        addTestData();

        int actual = taskService.getTaskList().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

}