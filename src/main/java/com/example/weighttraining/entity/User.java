package com.example.weighttraining.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String email;
    private String password;
    @OneToOne
    private RefreshToken refreshToken;
    @Builder
    public User( String nickName, String email,String password) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }


}
