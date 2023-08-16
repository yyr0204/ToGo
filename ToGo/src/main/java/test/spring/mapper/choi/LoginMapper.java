package test.spring.mapper.choi;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import test.spring.component.choi.KakaoDTO;

import java.util.Map;

@Repository
public interface LoginMapper {
	public KakaoDTO getUserInfo(String access_Token);
	public KakaoDTO kakaoNumber(KakaoDTO userInfo);
	public void kakaoInsert (KakaoDTO dto);
	public void addEmail(@Param("id") String id, @Param("email") String email);
	public int check(String id);
	public int check2(String email);
	public String mbtiCheck(String id);
	public void pwSetting(@Param("pw") String pw,@Param("email") String id);
	public int adminCheck(@Param("id") String id,@Param("pw") String pw);
	public int signup(Map<String,String> map);
}

