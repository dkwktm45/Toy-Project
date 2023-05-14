package com.example.weighttraining.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoController {

    @GetMapping("/kakao/login")
    public String login(){
        String url = "https://kauth.kakao.com/oauth/authorize?client_id=dd90fef97c072b58ed261dd3722b83b0&redirect_uri=http://localhost:5173/login/kakao&response_type=code";
        return url;
    }
}
