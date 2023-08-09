package test.spring.mapper.map;

import org.springframework.stereotype.Component;
import test.spring.component.choi.LoginDTO;
import test.spring.component.map.userDTO;

import java.util.List;

@Component
public interface userMapper {
    public userDTO profile_inquiry(String id);
    public int add_user_schedule(userDTO dto);
}
