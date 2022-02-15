package com.thisfit.shoppingmall.user.util.kakaologin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

@Component
public class KakaoUniqueNo {

	public HashMap<String, Object> getUserInfo (String access_Token){
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            System.out.println("element : " + element);
            
            JsonElement id = element.getAsJsonObject().get("id");
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            
            userInfo.put("id", id);
            userInfo.put("email", email);
            userInfo.put("nickname", nickname);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
	}
	
}
