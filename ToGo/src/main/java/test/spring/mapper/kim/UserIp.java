package test.spring.mapper.kim;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.kim.kimDTO;

@Mapper
public interface UserIp {
    public List<kimDTO> reco_place();
    
    public List<kimDTO> reco_place_user_main(Map<String, Object> params);

    public List<kimDTO> reco_place_user_sub(Map<String, Object> params);
}