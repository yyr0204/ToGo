package test.spring.service.song;

import java.util.List;
import test.spring.component.song.CityimgDTO;
import test.spring.component.song.SampleListDTO;

public interface TripService {
	
	public List<SampleListDTO> mainList(String table, List userAtmosphere);
	
	public List<SampleListDTO> mainList(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> subList(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> breaklunch(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);

	public List<SampleListDTO> abendessen(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> cityList();

	public List<CityimgDTO> cityimgList();
	
	public String tableName(String area);
	
	public String userMbti(String memId);
	
	public List userAtmosphere(String mbti);
	
}