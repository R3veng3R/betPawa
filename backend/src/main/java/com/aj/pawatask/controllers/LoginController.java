package com.aj.pawatask.controllers;

import com.aj.pawatask.models.User;
import com.aj.pawatask.services.impl.LoginServiceImpl;
import com.aj.pawatask.services.impl.UserServiceImpl;
import com.aj.pawatask.utils.dto.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private LoginServiceImpl loginService;
    private UserServiceImpl userService;

    public LoginController(LoginServiceImpl loginService, UserServiceImpl userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    /*
     * DEFAULT ERROR HANDLER IS LOCATED UNDER:
     * utils/errors/DefaultExceptionHandler.class
     */
    @PostMapping()
    public User authenticate(@RequestBody LoginDTO login) {
        return loginService.authenticate(login.getEmail());
    }


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
