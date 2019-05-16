package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.dto.TaskDTO;
import com.aj.pawatask.utils.enums.TaskPriority;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    private User testUser;
    private Task testTask;

    @Before
    public void setup() {
        testUser = new User();
        testUser.setLastName("TestUserLastName");
        testUser.setName("TestUserName");
        testUser.setEmail("test@test.com");
        userService.saveUser(testUser);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setTitle("TEST TITLE");
        taskDTO.setDescription("TEST DESCRIPTION");
        taskDTO.setComment(null);
        taskDTO.setUserId(testUser.getId());
        testTask = taskService.saveTask(taskDTO);
    }

    @After
    public void tearDown() {
        testUser = null;
        testTask = null;
    }

    @Test
    public void saveTaskTest() {
        List<Task> taskList = taskService.getTaskList();
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteTest() {
        taskService.deleteById(testTask.getId());
        Task actual = taskService.getById(testTask.getId());
        assertNull(actual);
    }
}