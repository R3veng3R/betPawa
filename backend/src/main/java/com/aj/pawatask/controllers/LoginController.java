package com.aj.pawatask.controllers;

import com.aj.pawatask.models.User;
import com.aj.pawatask.services.impl.LoginServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    public User authenticate(@RequestBody String email) {
        return loginService.authenticate(email);
    }
}
