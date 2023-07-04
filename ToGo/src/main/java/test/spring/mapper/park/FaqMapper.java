package test.spring.mapper.park;

import java.util.List;

import test.spring.component.park.FaqBoardDTO;

public interface FaqMapper {
	public int insert(FaqBoardDTO dto);
	public List<FaqBoardDTO> faqList(FaqBoardDTO dto);
}
