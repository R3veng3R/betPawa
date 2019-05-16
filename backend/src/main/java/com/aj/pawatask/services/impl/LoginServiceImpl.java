package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.User;
import com.aj.pawatask.services.LoginService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This is just a simple service to demonstrate authorization on the site.
 * In reality SPRING SECURITY should be used with JWT token.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private UserServiceImpl userService;

    public LoginServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public User authenticate(String email) {
        return userService.getUserByEmail(email);
    }
}
