package test.spring.service.song;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.spring.component.song.SampleListDTO;
import test.spring.mapper.song.TripMapper;

@Service
public class TripServiceImpl implements TripService{

	@Autowired
	private TripMapper mapper;
	
	@Override
	public List<SampleListDTO> mainList(String area) {
		return mapper.mainList(area);
	}
}
