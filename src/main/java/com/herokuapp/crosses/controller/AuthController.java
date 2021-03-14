package com.herokuapp.crosses.controller;

import com.herokuapp.crosses.model.persist.User;
import com.herokuapp.crosses.service.impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userService.create(user);
    }


    @PostMapping("login")
    public User signIn(@RequestBody User user) {
       return userService.authenticate(user);
    }
}