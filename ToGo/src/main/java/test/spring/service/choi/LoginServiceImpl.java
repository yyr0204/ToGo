package test.spring.service.choi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;
import test.spring.mapper.choi.LoginMapper;

@Service("ls")
	public class LoginServiceImpl implements LoginService{
		@Autowired
		private LoginMapper mapper;
		
		@Override
		public void kakaoInsert(KakaoDTO dto) {
		
			mapper.kakaoInsert(dto);
		
		}

		@Override		
		public HashMap<String, String> getUserInfo(String access_Token) {
			HashMap<String, String> userInfo = new HashMap<String, String>();
			String reqURL = "https://kapi.kakao.com/v2/user/me";
			
			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Bearer " + access_Token);
				int responseCode = conn.getResponseCode();
				System.out.println(responseCode);
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
				JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
				String nickname = properties.getAsJsonObject().get("nickname").getAsString();
				String email = kakao_account.getAsJsonObject().get("email").getAsString();
				userInfo.put("nickname", nickname);
				userInfo.put("email", email);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return userInfo;
		}
		
		@Override
		public String getAccessToken (String authorize_code) {
			String access_Token = "";
			String refresh_Token = "";
			String reqURL = "https://kauth.kakao.com/oauth/token";
			try {
				URL url = new URL(reqURL);
	            
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// POST �슂泥��쓣 �쐞�빐 湲곕낯媛믪씠 false�씤 setDoOutput�쓣 true濡�
	            
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				// POST �슂泥��뿉 �븘�슂濡� �슂援ы븯�뒗 �뙆�씪誘명꽣 �뒪�듃由쇱쓣 �넻�빐 �쟾�넚
	            
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");
	            
				sb.append("&client_id=bcc9d1aa7486b562e019afcd9ad3839b");
				sb.append("&redirect_uri=http://localhost:8080/spring/login/kakaologin");
	            
				sb.append("&code=" + authorize_code);
				bw.write(sb.toString());
				bw.flush();
	            
				int responseCode = conn.getResponseCode();
	            
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";
	            
				while ((line = br.readLine()) != null) {
					result += line;
				}
	            
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
	            
				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return access_Token;
		}
		@Override
		public void kakaoLogout(String access_Token) {
	        String reqURL = "https://kapi.kakao.com/v1/user/logout";
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);

	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	            String result = "";
	            String line = "";

	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println(result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
		public int check(String id) {			
			return mapper.check(id);
		}
		
		@Override
		public String mbtiCheck(String id) {
		
			return mapper.mbtiCheck(id);
		}

		
		@Override
		public void pwSetting(String pw, String id) {
			mapper.pwSetting(pw, id);
		}

		@Override
		public int adminCheck(String id, String pw) {
			return mapper.adminCheck(id, pw);
		}
		
}
