package test.spring.service.song;

import java.util.List;
<<<<<<< HEAD
=======

import test.spring.component.song.CityimgDTO;
>>>>>>> develop_Song
import test.spring.component.song.SampleListDTO;

public interface TripService {
	
	public List<SampleListDTO> mainList(String area);
	
	public List<SampleListDTO> mainList(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> subList(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> breakfast(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> luncheon(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> abendessen(String area, double minLat, double maxLat, double minLon, double maxLon);
	
	public List<SampleListDTO> cityList();
<<<<<<< HEAD
=======
	
	public List<CityimgDTO> cityimgList();
>>>>>>> develop_Song
	
}