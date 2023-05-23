package com.example.weighttraining.provider;

import com.example.weighttraining.config.data.TokenId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;


@Component
@Slf4j
public class JwtProvider {
    private final String KEY = "eHSKM9LuJmwoX6OwmryAbJxEkSZfHcuUnMO5DkqXSX4=";
    public boolean  validateAccessTokenExpiration(String  token){
        try{
            // 토큰 파싱
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token);

            return claims.getBody().getExpiration().before(new Date()); // 현재보다 만료가 이전인지 확인
        }
        catch (ExpiredJwtException ignored){
            return  true;
        }catch (Exception e){
            log.error("validateAccessToken error");
        }
        return false;
    }


    public String createAccessToken(long userId) {
        Date now = new Date();
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));

        Date expiration = new Date(now.getTime() + Duration.ofHours(2).toMillis());
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public TokenId getTokenId(byte[] decodedKey,String accessToken){
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(decodedKey)
                .build()
                .parseClaimsJws(accessToken);

        Long userId = Long.valueOf(claimsJws.getBody().getSubject());
        return new TokenId(userId);
    }
}
