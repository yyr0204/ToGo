package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.choi.KakaoDTO;

public interface MyPageMapper {
	public KakaoDTO user_info(String email);
	public KakaoDTO user_info2(String id);
	public int update_info(KakaoDTO dto);
	public int chStatus(@Param("email") String email,@Param("status") String status);
	public List<KakaoDTO> userList(KakaoDTO dto);
	public int userCount(KakaoDTO dto);
}
