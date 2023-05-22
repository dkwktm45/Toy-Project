package com.example.weighttraining.service;

import com.example.weighttraining.api.KaKaoAPI;
import com.example.weighttraining.entity.User;
import com.example.weighttraining.repository.UserRepostiory;
import com.example.weighttraining.request.UserLogin;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepostiory userRepostiory;

    private final KaKaoAPI kaKaoAPI = new KaKaoAPI();

    public void login(UserLogin userLogin) throws IllegalAccessException {
        if(userRepostiory.findByEmail(userLogin.getEmail()).isPresent()){
            log.info("존재하는 이메일");
            User user = userRepostiory.findByEmailAndPassword(userLogin.getEmail(),userLogin.getPassword())
                    .orElseThrow();
//            user.login(user.getPassword(), )
        }else{
            throw new IllegalAccessException("존재하지 않는 회원입니다.");
        }
    }



    public HashMap<String, Object> getUserKakaoInfo(String code) {
        String access_token = kaKaoAPI.getToken(code);

        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);


            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("resopnse body =" + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String email = null;
            String nickName = null;
            boolean has_email = element.getAsJsonObject()
                    .get("kakao_account")
                    .getAsJsonObject()
                    .get("has_email").getAsBoolean();
            if (has_email) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
                nickName = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
            }

            userInfo.put("email", email);
            userInfo.put("nickName", nickName);
            userInfo.put("access_token", access_token);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
