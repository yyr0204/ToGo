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
	public List<SampleListDTO> mainList(String table) {
		table = table+"_main";
		return mapper.mainList(table);
	}
	
	@Override
	public List<SampleListDTO> mainList(String table, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_main";
		return mapper.mainList2(table, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> subList(String table, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_sub";
		return mapper.subList(table, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> breaklunch(String table, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_sub";
		return mapper.breaklunch(table, minLat, maxLat, minLon, maxLon);
	}

	@Override
	public List<SampleListDTO> abendessen(String table, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_sub";
		return mapper.abendessen(table, minLat, maxLat, minLon, maxLon);
	}

	@Override
	public List<SampleListDTO> cityList() {
		return mapper.cityList();
	}

	@Override
	public List<CityimgDTO> cityimgList() {
		return mapper.cityimgList();
	}
	
	@Override
	public String tableName(String area) {
		return mapper.tableName(area);
	}

}
