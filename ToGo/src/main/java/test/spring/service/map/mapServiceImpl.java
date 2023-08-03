package test.spring.service.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.spring.component.map.listDTO;
import test.spring.component.map.mapDTO;
import test.spring.mapper.map.map_mapper;

import java.util.*;

@Service
public class mapServiceImpl implements mapService {
    @Autowired
    private map_mapper mapper;
    @Override
    public List<mapDTO> place() {
        return mapper.place();
    }

    @Override
    public mapDTO latlon(String area) {
        return mapper.latlon(area);
    }

    @Override
    public List<mapDTO>  place_list(listDTO dto) {
        return mapper.place_list(dto);
    }
}
