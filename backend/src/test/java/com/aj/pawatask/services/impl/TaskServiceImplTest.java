package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.dto.TaskDTO;
import com.aj.pawatask.utils.dto.mappers.TaskMapper;
import com.aj.pawatask.utils.enums.TaskPriority;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceImplTest {

    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private UserServiceImpl userService;

    private User testUser;

    @Before
    public void setup() {
        testUser = new User();
        testUser.setLastName("TestUserLastName");
        testUser.setName("TestUserName");
        testUser.setEmail("test@test.com");
        userService.saveUser(testUser);
    }

    @Test
    public void deleteTest() {
        Task deleteTask = new Task("Test1", "descriptionTest", new Date(), TaskPriority.HIGH, testUser);
        taskService.saveTask(deleteTask);

        taskService.deleteById(deleteTask.getId());
        Task actual = taskService.getById(deleteTask.getId());
        assertNull(actual);
    }

    @Test
    public void saveTaskTest() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setTitle("TEST TITLE");
        taskDTO.setDescription("TEST DESCRIPTION");
        taskDTO.setComment(null);
        taskDTO.setUserId(testUser.getId());
        Task actual = taskService.saveTask(taskDTO);

        List<Task> taskList = taskService.getTaskList();
        assertTrue(taskList.size() > 0);

        taskService.deleteById(actual.getId()); // REMOVE TEST DATA
    }

    @Test
    public void pagingTest() {
        List<Task> taskList = new ArrayList<>();
        taskList.add (new Task("Test1", "descriptionTest", new Date(), TaskPriority.HIGH, testUser));
        taskList.add (new Task("Test2", "descriptionTest", new Date(), TaskPriority.HIGH, testUser));
        taskList.add (new Task("Test3", "descriptionTest", new Date(), TaskPriority.HIGH, testUser));
        taskList.add (new Task("Test4", "descriptionTest", new Date(), TaskPriority.HIGH, testUser));
        taskList.add (new Task("Test5", "descriptionTest", new Date(), TaskPriority.HIGH, testUser));
        taskService.saveAll(taskList);

        int page = 0;
        int pageSize = 3;

        Page<Task> taskPage = taskService.getTaskList(page, pageSize);
        assertEquals(pageSize, taskPage.getContent().size());
    }
}