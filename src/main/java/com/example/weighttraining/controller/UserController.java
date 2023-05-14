package com.example.weighttraining.controller;

import com.example.weighttraining.request.UserLogin;
import com.example.weighttraining.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) throws IllegalAccessException {
        userService.login(userLogin);
        return "ok";
    }

}
