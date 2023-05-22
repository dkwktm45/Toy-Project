package com.example.weighttraining.jwt;

import com.example.weighttraining.entity.User;
import com.example.weighttraining.repository.UserRepostiory;
import com.example.weighttraining.request.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final UserRepostiory userRepostiory;
    private final String KEY = "eHSKM9LuJmwoX6OwmryAbJxEkSZfHcuUnMO5DkqXSX4=";

    public String  login(UserLogin userLogin) throws IllegalAccessException {
        User user = userRepostiory.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new IllegalAccessException("존재하지 않는 회원입니다."));
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofHours(2).toMillis());
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(String.valueOf(user.getId()))
                .signWith(key).compact();
    }

}
