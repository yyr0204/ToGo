package test.spring.mapper.choi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;

public interface LoginMapper {
	public KakaoDTO getUserInfo(String access_Token);
	public KakaoDTO kakaoNumber(KakaoDTO userInfo);
	public void kakaoInsert (KakaoDTO dto);
	public int check(String id);
}

