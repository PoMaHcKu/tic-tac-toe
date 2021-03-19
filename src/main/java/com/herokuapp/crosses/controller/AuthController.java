package com.herokuapp.crosses.controller;

import com.herokuapp.crosses.model.persist.User;
import com.herokuapp.crosses.service.impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.herokuapp.crosses.WebConstants.LOGIN_ENDPOINT;
import static com.herokuapp.crosses.WebConstants.REGISTER_ENDPOINT;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(REGISTER_ENDPOINT)
    public User register(@RequestBody User user) {
        return userService.create(user);
    }


    @PostMapping(LOGIN_ENDPOINT)
    public User signIn(@RequestBody User user) {
       return userService.authenticate(user);
    }
}