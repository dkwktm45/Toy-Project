package com.example.weighttraining.service;

import com.example.weighttraining.repository.UserRepostiory;
import com.example.weighttraining.request.UserLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepostiory userRepostiory;
    public void login(UserLogin userLogin) throws IllegalAccessException {
        if(userRepostiory.findByEmail(userLogin.getEmail()).isPresent()){
            log.info("존재하는 이메일");
            userRepostiory.findByEmailAndPassword(userLogin.getEmail(),userLogin.getPassword())
                    .orElseThrow();
        }else{
            throw new IllegalAccessException("존재하지 않는 회원입니다.");
        };
    }
}
