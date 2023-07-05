package test.spring.mapper.song;

import java.util.List;

import test.spring.component.song.sampleListDTO;

public interface TripMapper {

	public List<sampleListDTO> mainList(String area);
	
}
