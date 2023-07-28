package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.choi.KakaoDTO;

public interface MyPageMapper {
	public KakaoDTO user_info(String id);
	public int update_info(KakaoDTO dto);
	public int chStatus(@Param("id") String id,@Param("status") String status);
	public List<KakaoDTO> userList(KakaoDTO dto);
	public int userCount(KakaoDTO dto);
}
