package com.example.weighttraining.controller;

import com.example.weighttraining.config.data.TokenId;
import com.example.weighttraining.entity.User;
import com.example.weighttraining.service.JwtService;
import com.example.weighttraining.request.UserLogin;
import com.example.weighttraining.response.TokenResponse;
import com.example.weighttraining.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class JwtController {

    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/Auth/login")
    public TokenResponse login(@RequestBody UserLogin userLogin) throws IllegalAccessException {
        User user = userService.findUser(userLogin.getEmail());

        return new TokenResponse(jwtService.login(user), jwtService.uuid(user));
    }

    @GetMapping("/foo")
    public Long foo(TokenId tokenId){
        return tokenId.id;
    }
}
