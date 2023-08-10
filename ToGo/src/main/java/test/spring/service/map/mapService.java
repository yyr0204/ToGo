package test.spring.service.map;

import test.spring.component.map.listDTO;
import test.spring.component.map.mapDTO;

import java.util.List;
import java.util.Map;

public interface mapService {
    public List<mapDTO> place();
    public mapDTO latlon(String area);
    public List<mapDTO>  place_list(listDTO dto);
    public List<mapDTO> search(Map<String,String>map);
}
