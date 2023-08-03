package test.spring.mapper.song;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import test.spring.component.song.CityimgDTO;
import test.spring.component.song.ImageBoard1DTO;
import test.spring.component.song.SampleListDTO;

public interface TripMapper {

	public List<SampleListDTO> mainList(String table);
	
	public List<SampleListDTO> mainList2(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> subList(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> breaklunch(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);

	public List<SampleListDTO> abendessen(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public SampleListDTO cityList(Map<String,String>place_bag);
	
	public List<CityimgDTO> cityimgList();
	
	public String tableName(String area);
	
	public String userMbti(String memId);
	
	public List<String> userAtmosphere(String mbti);
	
	public List<ImageBoard1DTO> wePlan();
	
}
