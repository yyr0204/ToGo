package test.spring.service.park;

import java.util.List;

import test.spring.component.park.FaqBoardDTO;

public interface FaqService {
	public int insert(FaqBoardDTO dto);
	public List<FaqBoardDTO> faqList(FaqBoardDTO dto);
}
