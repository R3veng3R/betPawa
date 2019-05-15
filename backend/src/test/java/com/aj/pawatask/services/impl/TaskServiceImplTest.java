package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.dto.TaskDTO;
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

    private User testUser;

    private void addTestData() {
        testUser = new User();
        testUser.setLastName("TestUserLastName");
        testUser.setName("TestUserName");
        userService.saveUser(testUser);
    }

    @Test
    public void saveTaskTest() {
        addTestData();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setTitle("TEST TITLE");
        taskDTO.setDescription("TEST DESCRIPTION");
        taskDTO.setComment("TEST COMMENT");
        taskDTO.setUserId(testUser.getId());
        taskService.saveTask(taskDTO);

        List<Task> taskList = taskService.getTaskList();
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteTest() {
//        Task taskAdded = addTestData();
//        taskService.delete(taskAdded);
//        Task actual = taskService.getById(taskAdded.getId());
//        assertNull(actual);
    }
}