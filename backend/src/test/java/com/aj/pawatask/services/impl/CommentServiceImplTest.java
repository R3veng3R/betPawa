package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.models.Task;
import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.dto.CommentDTO;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

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
        testUser.setEmail("mytest@email.com");
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
        assertTrue(commentList.size() > 0);

        Comment comment = commentList.get(0);
        assertNotNull(comment.getUser());
    }
}