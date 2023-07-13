package test.spring.service.choi;

import java.util.HashMap;
import java.util.List;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;

public interface LoginService {
	public void insert(LoginDTO dto);

	public Object blogAll(LoginDTO dto);

	public List<LoginDTO> all();

	public void delete(int num);

	void update(int num, String content);

	public String getAccessToken (String authorize_code);
	
	public static final MemberRepository mr = new MemberRepository();

	public void kakaoInsert(Object nick,Object email);

	public KakaoDTO getUserInfo(String access_Token);

	public KakaoDTO kakaoNumber(KakaoDTO userInfo);
}
