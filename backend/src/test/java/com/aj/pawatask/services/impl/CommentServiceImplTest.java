package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.dto.CommentDTO;
import com.aj.pawatask.utils.dto.TaskDTO;
import com.aj.pawatask.utils.enums.TaskPriority;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private CommentServiceImpl commentService;

    private Task testTask;
    private User testUser;

    @Before
    public void setup() {
        insertTestData();
    }

    private void insertTestData() {
        testUser = new User();
        testUser.setLastName("myTestUserLastname");
        testUser.setName("myTestUserName");
        userService.saveUser(testUser);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setTitle("TEST TITLE");
        taskDTO.setDescription("TEST DESCRIPTION");
        taskDTO.setComment("TEST COMMENT");
        taskDTO.setUserId(testUser.getId());
        testTask = taskService.saveTask(taskDTO);
    }

    @Test
    public void saveComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setTaskId(testTask.getId());
        commentDTO.setUserId(testUser.getId());
        commentDTO.setComment("test comment");

        assertNotNull(commentService.saveComment(commentDTO));
    }

    @Test
    public void getComments() {
        saveComment();

        Long taskId = testTask.getId();
        List<Comment> commentList = commentService.getTaskComments(taskId);
        assertEquals(1, commentList.size());

        Comment comment = commentList.get(0);
        assertNotNull(comment.getUser());
    }
}