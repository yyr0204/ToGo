package test.spring.mapper.map;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import test.spring.component.map.listDTO;
import test.spring.component.map.userDTO;

@Component
public interface userMapper {
    public userDTO profile_inquiry(String id);
    public int add_user_schedule(userDTO dto);
    public int user_tour_info(Map<String,String> user_info);
    public void placeCount(@Param("area") String area, @Param("name") String name);
}
