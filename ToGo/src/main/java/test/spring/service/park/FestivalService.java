package test.spring.service.park;

import java.util.List;

import test.spring.component.park.FstvlDTO;


public interface FestivalService {
	public List<FstvlDTO> getRandomFstvlList(FstvlDTO dto);
	public List<FstvlDTO> fstvlList(FstvlDTO dto);
	public int selectFstvlCount(FstvlDTO dto);
}
