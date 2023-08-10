package test.spring.service.map;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.spring.component.map.userDTO;
import test.spring.mapper.map.userMapper;
import test.spring.mapper.song.TripMapper;

@Service
public class userServiceImpl implements userService{
    @Autowired
    private userMapper mapper;
    @Autowired
    private TripMapper mapper2;
    
    @Override
    public userDTO profile_inquiry(String id) {
        return mapper.profile_inquiry(id);
    }

    @Override
    public int add_user_schedule(userDTO dto) {
        return mapper.add_user_schedule(dto);
    }
    
    @Override
    public int user_tour_info(Map<String, String> user_info) {
        return mapper.user_tour_info(user_info);
    }
    
    @Transactional
    @Override
    public void placeCount(userDTO dto) {
    	String area = mapper2.tableName(dto.getArea());
    	String areaSub = area+"_sub";
    	String areaMain = area+"_main";
    	List list = dto.getCourse();
    	System.out.println(areaSub);
    	System.out.println(areaMain);
    	System.out.println(dto.getCourse());
    	mapper.placeCount(areaSub, (String)list.get(0));
    	System.out.println(1);
    	mapper.placeCount(areaMain, (String)list.get(1));
    	System.out.println(2);
    	mapper.placeCount(areaSub, (String)list.get(2));
    	System.out.println(3);
    	mapper.placeCount(areaSub, (String)list.get(3));
    	System.out.println(4);
    	mapper.placeCount(areaMain, (String)list.get(4));
    	System.out.println(5);
    	mapper.placeCount(areaSub, (String)list.get(5));
    	System.out.println(6);
    }
}
