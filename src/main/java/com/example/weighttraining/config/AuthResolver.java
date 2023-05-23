package com.example.weighttraining.config;

import com.example.weighttraining.config.data.TokenId;
import com.example.weighttraining.entity.RefreshToken;
import com.example.weighttraining.provider.JwtProvider;
import com.example.weighttraining.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {
    private static final String KEY = "eHSKM9LuJmwoX6OwmryAbJxEkSZfHcuUnMO5DkqXSX4=";
    private static final JwtProvider jwtProvider = new JwtProvider();
    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getGenericParameterType().equals(TokenId.class);
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader("Authorization");
        String refreshToken = webRequest.getHeader("refresh-authorization");
        if (accessToken == null) {
            log.error("httpServletRequest is null");
            throw new IllegalAccessException();
        }

        byte[] decodedKey = Base64.decodeBase64(KEY);

        if(!jwtProvider.validateAccessTokenExpiration(accessToken)){
            // access token이 만료되지 않았다면 복호화 작업
            return jwtProvider.getTokenId(decodedKey, accessToken);
        }
        else {
            Optional<RefreshToken> refreshTokenDB = jwtService.uuidVaild(refreshToken);
            if(refreshTokenDB.isPresent()){
                // access token이 만료되지 않았다면 복호화 작업
                String  newAccessToken = jwtProvider.createAccessToken(refreshTokenDB.get().getId());

                // Refresh token 사용이 있을 때는 다시 재발급
                jwtService.updateToken(refreshTokenDB);

                return jwtProvider.getTokenId(decodedKey , newAccessToken);
            }
            throw new IllegalAccessException("인가되지 않는 사용자입니다.");
        }
    }
}
