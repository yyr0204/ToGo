package test.spring.mapper.park;

import test.spring.component.choi.KakaoDTO;

public interface MyPageMapper {
	public KakaoDTO user_info(String id);
	public int update_info(KakaoDTO dto);
}
