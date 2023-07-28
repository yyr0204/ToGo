package test.spring.component.kim;

import java.util.List;

import lombok.Data;

@Data
public class CityAndPlaces {
	
	private String city;
    private List<kimDTO> mainPlaces;
    private List<kimDTO> subPlaces;
    private List<kimDTO> places;

}
