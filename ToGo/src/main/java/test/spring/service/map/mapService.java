package test.spring.service.map;

import test.spring.component.map.mapDTO;

import java.util.List;

public interface mapService {
    public List<mapDTO> place();
    public mapDTO latlon(String area);
}
