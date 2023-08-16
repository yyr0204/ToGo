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

    @Override
    public List<mapDTO> search(Map<String, String> map) {
        map.put("table",map.get("area")+"_main");
        List<mapDTO> list = mapper.search_list(map); //식당,카페등을 제외한 주요 관광지가 담긴 테이블에서 데이터 조회
        map.replace("table",map.get("area")+"_sub");
        List<mapDTO> list2 = mapper.search_list(map); //식당,카페등이 담긴 테이블에서 데이터 조회
        list.addAll(list2); //두 값을 더해서 넘기기
        return list;
    }


}
