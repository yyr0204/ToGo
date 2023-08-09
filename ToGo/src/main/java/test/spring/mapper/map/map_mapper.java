package test.spring.mapper.map;

import org.springframework.stereotype.Component;
import test.spring.component.map.listDTO;
import test.spring.component.map.mapDTO;

import java.util.List;
import java.util.Map;

@Component
public interface map_mapper {
    public List<mapDTO> place();
    public mapDTO latlon(String area);
    public List<mapDTO> place_list(listDTO dto);
    public List<String> search_list(Map<String,String>map);
}
