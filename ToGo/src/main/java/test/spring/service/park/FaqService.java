package test.spring.service.park;

import test.spring.component.park.FaqBoardDTO;

public interface FaqService {
	public int insert(FaqBoardDTO dto);
	public FaqBoardDTO faqList(FaqBoardDTO dto);
}
