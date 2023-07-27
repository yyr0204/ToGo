package test.spring.mapper.map;

import org.springframework.stereotype.Component;
import test.spring.component.map.mapDTO;

import java.util.List;
@Component
public interface map_mapper {
    public List<mapDTO> place();
    public mapDTO latlon(String area);
}
