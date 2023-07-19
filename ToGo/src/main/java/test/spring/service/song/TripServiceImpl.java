package test.spring.service.song;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.song.CityimgDTO;
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
	
	@Override
	public List<SampleListDTO> mainList(String area, double minLat, double maxLat, double minLon, double maxLon) {
		return mapper.mainList2(area, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> subList(String area, double minLat, double maxLat, double minLon, double maxLon) {
		return mapper.subList(area, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> breakfast(String area, double minLat, double maxLat, double minLon, double maxLon) {
		return mapper.breakfast(area, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> luncheon(String area, double minLat, double maxLat, double minLon, double maxLon) {
		return mapper.luncheon(area, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> abendessen(String area, double minLat, double maxLat, double minLon, double maxLon) {
		return mapper.abendessen(area, minLat, maxLat, minLon, maxLon);
	}

	@Override
	public List<SampleListDTO> cityList() {
		return mapper.cityList();
	}
	
	@Override
	public List<CityimgDTO> cityimgList() {
		return mapper.cityimgList();
	}

}
