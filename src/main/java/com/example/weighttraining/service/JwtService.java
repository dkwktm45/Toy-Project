package com.example.weighttraining.service;

import com.example.weighttraining.entity.RefreshToken;
import com.example.weighttraining.entity.User;
import com.example.weighttraining.repository.RefreshTokenRepostiory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final RefreshTokenRepostiory repostiory;
    private final String KEY = "eHSKM9LuJmwoX6OwmryAbJxEkSZfHcuUnMO5DkqXSX4=";

    public String  login(User user) throws IllegalAccessException {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(30).toMillis());

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(String.valueOf(user.getId()))
                .signWith(key).compact();
    }

    public String uuid(User user){
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshUuid(String.valueOf(UUID.randomUUID()))
                .user(user)
                .build();
        return repostiory.save(refreshToken).getRefreshUuid();
    }
    public Optional<RefreshToken> uuidVaild(String refreshUuid){
        return repostiory.findByRefreshUuid(refreshUuid);
    }

    @Transactional
    public void updateToken(Optional<RefreshToken> refreshTokenDB) {
        refreshTokenDB.get().setNewRefresh();
    }
}
