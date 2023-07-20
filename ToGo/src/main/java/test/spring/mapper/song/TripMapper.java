package test.spring.mapper.song;

import java.util.List;

import org.apache.ibatis.annotations.Param;

<<<<<<< HEAD
=======
import test.spring.component.song.CityimgDTO;
>>>>>>> develop_Song
import test.spring.component.song.SampleListDTO;

public interface TripMapper {

	public List<SampleListDTO> mainList(String area);
	
	public List<SampleListDTO> mainList2(@Param("area") String area, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> subList(@Param("area") String area, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> breakfast(@Param("area") String area, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> luncheon(@Param("area") String area, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> abendessen(@Param("area") String area, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> cityList();
<<<<<<< HEAD
=======
	
	public List<CityimgDTO> cityimgList();
>>>>>>> develop_Song
	
}
