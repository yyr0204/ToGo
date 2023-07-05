package test.spring.mapper.song;

import java.util.List;

import test.spring.component.song.SampleListDTO;

public interface TripMapper {

	public List<SampleListDTO> mainList(String area);
	
}
