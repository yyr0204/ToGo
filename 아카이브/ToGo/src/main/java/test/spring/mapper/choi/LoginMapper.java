package test.spring.mapper.choi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;

public interface LoginMapper {
//	public void insert(LoginDTO dto);
//	public List<LoginDTO> blogList();
//	public Object blogAll(LoginDTO dto);
	public void delete(int num);
	public void update(@Param("num")int num,@Param("content")String content);
	public KakaoDTO getUserInfo(String access_Token);
	public KakaoDTO kakaoNumber(KakaoDTO userInfo);
	public void kakaoInsert (@Param("nickname")String nickname,@Param("email")String email);
	public void insert(LoginDTO dto);
	public Object blogAll(LoginDTO dto);
	public List<LoginDTO> blogList();
}

