package com.example.weighttraining.controller;

import com.example.weighttraining.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/kakao/login")
    public RedirectView login(@RequestParam String code, HttpSession session){
        RedirectView redirectView = new RedirectView();

        HashMap<String, Object> userInfo = userService.getUserKakaoInfo(code);
        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_token", userInfo.get("access_token"));
        }

        redirectView.addStaticAttribute("email", userInfo.get("email"));
        redirectView.setUrl("http://localhost:5173");
        return redirectView;
    }
}
