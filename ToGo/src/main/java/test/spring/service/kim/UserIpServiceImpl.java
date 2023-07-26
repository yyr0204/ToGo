package test.spring.service.kim;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.kim.kimDTO;
import test.spring.mapper.kim.UserIp;
@Service
public class UserIpServiceImpl implements UserIpService{

	@Autowired
	private UserIp mapper;
	
	@Override
	public List<kimDTO> reco_place() {
		return mapper.reco_place();
	}

}
