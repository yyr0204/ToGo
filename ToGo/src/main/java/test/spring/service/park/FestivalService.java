package test.spring.service.park;

import java.util.List;

import test.spring.component.park.FstvlDTO;


public interface FestivalService {
	public List<FstvlDTO> fstvlList(FstvlDTO dto);
	// 게시물 개수
	public int selectFstvlCount(FstvlDTO dto);
}
