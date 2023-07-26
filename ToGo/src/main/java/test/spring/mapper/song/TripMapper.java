package test.spring.mapper.song;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import test.spring.component.song.CityimgDTO;
import test.spring.component.song.SampleListDTO;

public interface TripMapper {

	public List<SampleListDTO> mainList(String table);
	
	public List<SampleListDTO> mainList2(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> subList(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> breaklunch(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);

	public List<SampleListDTO> abendessen(@Param("table") String table, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> cityList();
	
	public List<CityimgDTO> cityimgList();
	
	public String tableName(String area);
	
}
