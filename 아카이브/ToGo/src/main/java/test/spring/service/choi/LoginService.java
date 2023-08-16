package test.spring.service.choi;

import java.util.HashMap;
import java.util.List;

import test.spring.component.choi.LoginDTO;

public interface LoginService {
	public void insert(LoginDTO dto);

	public Object blogAll(LoginDTO dto);

	public List<LoginDTO> all();

	public void delete(int num);

	void update(int num, String content);

	public String getAccessToken (String authorize_code) ;
	
	public static final MemberRepository mr = new MemberRepository();
	public HashMap<String, String> getUserInfo(String access_Token);

	public void kakaoInsert(String nickname,String email);


}
