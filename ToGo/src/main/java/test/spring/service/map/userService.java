package test.spring.service.map;

import test.spring.component.choi.LoginDTO;
import test.spring.component.map.userDTO;

import java.util.List;
import java.util.Map;

public interface userService {
    public userDTO profile_inquiry(String id);
    public int add_user_schedule(userDTO dto);
    public int user_tour_info(Map<String,String> user_info);
    public void placeCount(userDTO dto);
}
