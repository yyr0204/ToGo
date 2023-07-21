package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.park.FaqBoardDTO;
@Mapper
public interface FaqMapper {
	public int insert(FaqBoardDTO dto);
	public List<FaqBoardDTO> faqList(FaqBoardDTO dto);
}
