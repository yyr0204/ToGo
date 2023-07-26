package test.spring.mapper.park;

import org.apache.ibatis.annotations.Param;

import test.spring.component.choi.KakaoDTO;

public interface MyPageMapper {
	public KakaoDTO user_info(@Param("id") String id,@Param("pw") String pw);
	public int update_info(KakaoDTO dto);
}
