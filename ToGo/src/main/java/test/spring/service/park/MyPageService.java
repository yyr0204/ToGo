package test.spring.service.park;

import java.util.List;

import test.spring.component.choi.KakaoDTO;

public interface MyPageService {
	public KakaoDTO user_info(String email);
	public KakaoDTO user_info2(String id);
	public int update_info(KakaoDTO dto);
	public List<KakaoDTO> userList(KakaoDTO dto);
	public int userCount(KakaoDTO dto);
	public int chStatus(String email,String status);
}
