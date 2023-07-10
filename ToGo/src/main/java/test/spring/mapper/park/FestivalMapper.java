package test.spring.mapper.park;

import java.util.List;

import test.spring.component.park.FstvlDTO;

public interface FestivalMapper {
	public List<FstvlDTO> fstvlList(FstvlDTO dto);
//	public FstvlDTO testCrawling(String testURL);
//	void insertFestivals(FstvlDTO festival);

}
