package test.spring.service.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.choi.KakaoDTO;
import test.spring.mapper.park.MyPageMapper;

@Service
public class MyPageServiceImpl implements MyPageService{
	@Autowired
	private MyPageMapper mapper;
	@Override
	public KakaoDTO user_info(String id) {
		return mapper.user_info(id);
	}
	@Override
	public int update_info(KakaoDTO dto) {
		return mapper.update_info(dto);
	}

}
