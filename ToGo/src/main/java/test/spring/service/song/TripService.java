package test.spring.service.song;

import java.util.List;
import java.util.Map;

import test.spring.component.map.userDTO;
import test.spring.component.song.CityimgDTO;
import test.spring.component.song.ImageBoard1DTO;
import test.spring.component.song.SampleListDTO;

public interface TripService {
	
	public List<SampleListDTO> mainList(String table, List userAtmosphere);
	
	public List<SampleListDTO> mainList(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> subList(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> breaklunch(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);

	public List<SampleListDTO> abendessen(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon);
	
	public SampleListDTO cityList(Map<String,String>place_bag);

	public List<CityimgDTO> cityimgList();
	
	public String tableName(String area);
	
	public String userMbti(String memId);
	
	public List userAtmosphere(String mbti);
	
	public List<ImageBoard1DTO> wePlan();
	
	public List<userDTO> userPlan(String memId);
	
	public List<userDTO> userPlan2(String plan_num);
	
	public List<SampleListDTO> popular(String table);
	
	public int userPlanCount(String memId);
	
}