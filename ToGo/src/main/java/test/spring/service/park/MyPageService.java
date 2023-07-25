package test.spring.service.park;

import test.spring.component.choi.KakaoDTO;

public interface MyPageService {
	public KakaoDTO user_info(String id, String pw);
	public int update_info(KakaoDTO dto);
}
