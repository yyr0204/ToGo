package test.spring.service.park;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.park.FaqBoardDTO;
import test.spring.mapper.park.FaqMapper;


@Service("faqservice")
public class FaqServiceImpl implements FaqService{
	@Autowired
	private FaqMapper mapper;
	
	@Override
	public int insert(FaqBoardDTO dto) {
		return mapper.insert(dto);
	}

	@Override
	public List<FaqBoardDTO> faqList(FaqBoardDTO dto) {
	    return mapper.faqList(dto);
	}
}
