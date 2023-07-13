package test.spring.mapper.song;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.song.SampleListDTO;

public interface TripMapper {

	public List<SampleListDTO> mainList(String area);
	
	public List<SampleListDTO> subList(@Param("area") String area, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon, @Param("maxLon") double maxLon);
	
	public List<SampleListDTO> breakfast(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> luncheon(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> abendessen(String area, double minLat, double maxLat, double minLon, double maxLon);
	public List<SampleListDTO> mainList2(String area);
	public void insertList(SampleListDTO dto);

}
