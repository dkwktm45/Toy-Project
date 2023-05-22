package com.example.weighttraining.controller;

import com.example.weighttraining.config.data.Token;
import com.example.weighttraining.jwt.JwtService;
import com.example.weighttraining.request.UserLogin;
import com.example.weighttraining.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;
    private final String KEY = "eHSKM9LuJmwoX6OwmryAbJxEkSZfHcuUnMO5DkqXSX4=";
    @PostMapping("/Auth/login")
    public TokenResponse login(@RequestBody UserLogin userLogin) throws IllegalAccessException {
        return new TokenResponse(jwtService.login(userLogin));
    }

    @GetMapping("/foo")
    public Long foo(Token token){
        return token.id;
    }
}
