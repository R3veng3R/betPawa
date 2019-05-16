package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void saveUser() {
        User user = new User();
        user.setLastName("MyLastName");
        user.setName("MyName");
        user.setActive(true);
        user.setEmail("myEmail@test.com");

        assertNotNull(userService.saveUser(user));
    }
}