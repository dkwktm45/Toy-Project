package com.example.weighttraining.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class KaKaoAPI {

    public String getToken(String code) {

        String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            // 해당 주소의 페이지로 접속
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //InputStream으로 응답 헤더와 메시지를 읽어들이겠다는 옵션을 정의한다.
            conn.setDoOutput(true);

            //writer: 출력, reader: 입력
            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=dd90fef97c072b58ed261dd3722b83b0");
            sb.append("&redirect_url=http://localhost:8080/kakao/login");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            // 그리고 스트림의 버퍼를 비워준다.
            bw.flush();

            int responsCode = conn.getResponseCode();
            System.out.println("3. responsCode = " + responsCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            //파일의 한줄한줄을 읽어서 출력한다.
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("resopnsebody = " + result);

            //json 형식으로 파시변환
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            // 스트림을 닫는다.
            br.close();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }
}
