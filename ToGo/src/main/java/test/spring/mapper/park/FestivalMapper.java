package test.spring.mapper.park;

import java.util.List;


import test.spring.component.park.FstvlDTO;
public interface FestivalMapper {
	public List<FstvlDTO> fstvlList(FstvlDTO dto);
	// 게시글의 개수를 조회
	public int selectFstvlCount(FstvlDTO dto);

}
