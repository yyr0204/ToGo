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
		public void insert(LoginDTO dto) {
			mapper.insert(dto);
		}

		@Override
		public Object blogAll(LoginDTO dto) {
			return mapper.blogAll(dto);
		}

		@Override
		public List<LoginDTO> all() {
			return mapper.blogList();
		}
		@Override
		public void kakaoInsert(String nickname,String email) {
			System.out.println(nickname);
			System.out.println(email);
			mapper.kakaoInsert(nickname, email);;
			System.out.println(email);
		}
		@Override
		public void delete(int num) {
			mapper.delete(num);
		}
		@Override
		public void update(int num, String content) {
			mapper.update(num, content);
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
				// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");
	            
				sb.append("&client_id=f8ad76ca44538cce53ddd7af32a8a93a"); //본인이 발급받은 key
				sb.append("&redirect_uri=http://localhost:8080/spring/login/kakaologin"); // 본인이 설정한 주소
	            
				sb.append("&code=" + authorize_code);
				bw.write(sb.toString());
				bw.flush();
	            
				int responseCode = conn.getResponseCode();
	            
				// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";
	            
				while ((line = br.readLine()) != null) {
					result += line;
				}
	            
				// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
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

	
}
