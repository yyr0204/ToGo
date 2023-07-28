package test.spring.service.park;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.choi.KakaoDTO;
import test.spring.mapper.park.MyPageMapper;

@Service("mpservice")
public class MyPageServiceImpl implements MyPageService{
	@Autowired
	private MyPageMapper mapper;
	@Override
	public KakaoDTO user_info(String email) {
		return mapper.user_info(email);
	}
	@Override
	public int update_info(KakaoDTO dto) {
		return mapper.update_info(dto);
	}
	@Override
	public List<KakaoDTO> userList(KakaoDTO dto) {
		return mapper.userList(dto);
	}
	@Override
	public int userCount(KakaoDTO dto) {
		return mapper.userCount(dto);
	}
	@Override
	public int chStatus(String email, String status) {
		return mapper.chStatus(email, status);
	}

}
