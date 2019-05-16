package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.User;
import com.aj.pawatask.utils.errors.EmailNotFoundExcetion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    private User testUser;

    @Before
    public void addTestUser() {
        testUser = new User();
        testUser.setLastName("TestUserLastName");
        testUser.setName("TestUserName");
        testUser.setActive(true);
        testUser.setEmail("testUser@gmail.com");
        userService.saveUser(testUser);
    }

    @Test
    public void authenticate_should_find_user() {
        User user = userService.getUserByEmail(testUser.getEmail());

        assertNotNull(user);
        assertEquals(testUser.getId(), user.getId());
    }

    @Test(expected = EmailNotFoundExcetion.class)
    public void authenticate_should_throw_exception() {
        userService.getUserByEmail("unknown@email.com");
    }
}