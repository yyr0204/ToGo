package test.spring.service.song;

import java.util.List;
import test.spring.component.song.SampleListDTO;

public interface TripService {
	
	public List<SampleListDTO> mainList(String area);
	
	public List<SampleListDTO> subList(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> breakfast(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> luncheon(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> abendessen(String area, double minLat, double maxLat, double minLon, double maxLon);
	public List<SampleListDTO> cityList(String area);
	public List<SampleListDTO> subList2(String area, double minLat, double maxLat, double minLon, double maxLon);
	public void insertList(SampleListDTO dto);
	
}