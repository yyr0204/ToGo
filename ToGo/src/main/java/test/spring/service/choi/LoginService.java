package test.spring.service.choi;

import java.util.HashMap;
import java.util.List;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;

public interface LoginService {

	public String getAccessToken (String authorize_code) ;
	
	public static final MemberRepository mr = new MemberRepository();
	
	public HashMap<String, String> getUserInfo(String access_Token);
	
	public void kakaoInsert(KakaoDTO dto);

	public void kakaoLogout(String access_Token);

	public int check(String id);
	
	public String mbtiCheck(String id);
}
