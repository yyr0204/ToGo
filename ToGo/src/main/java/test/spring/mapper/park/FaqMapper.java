package test.spring.mapper.park;

import test.spring.component.park.FaqBoardDTO;

public interface FaqMapper {
	public int insert(FaqBoardDTO dto);
	public FaqBoardDTO faqList(FaqBoardDTO dto);
}
