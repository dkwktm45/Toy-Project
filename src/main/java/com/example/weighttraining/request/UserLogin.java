package com.example.weighttraining.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLogin {

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
    @NotBlank(message = "비밀번호을 입력해주세요")
    private String password;

    @Builder
    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
