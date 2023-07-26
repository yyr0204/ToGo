package test.spring.service.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.spring.component.choi.LoginDTO;
import test.spring.component.map.userDTO;
import test.spring.mapper.map.userMapper;

import java.util.List;
@Service
public class userServiceImpl implements userService{
    @Autowired
    private userMapper mapper;
    @Override
    public userDTO profile_inquiry(String id) {
        return mapper.profile_inquiry(id);
    }
}
