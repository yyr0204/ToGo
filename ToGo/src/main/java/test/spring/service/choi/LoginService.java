package test.spring.service.choi;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;

public interface LoginService {

	public String getAccessToken (String authorize_code) ;
	
	public static final MemberRepository mr = new MemberRepository();
	
	public HashMap<String, String> getUserInfo(String access_Token);
	
	public void kakaoInsert(KakaoDTO dto);
	
	public void addEmail(String id, String email);

	public void kakaoLogout(String access_Token);

	public int check(String id);
	
	public int check2(String email);
	
	public String mbtiCheck(String id);
	public void pwSetting(String pw,String id);
	public int adminCheck( String id, String pw);
}
