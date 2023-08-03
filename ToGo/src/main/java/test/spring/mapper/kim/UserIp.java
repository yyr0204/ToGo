package test.spring.mapper.kim;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.kim.RewardDTO;
import test.spring.component.kim.Reward_GoodsDTO;
import test.spring.component.kim.kimDTO;

@Mapper
public interface UserIp {
    public List<kimDTO> reco_place();
    
    public List<kimDTO> reco_place_user_main(Map<String, Object> params);

    public List<kimDTO> reco_place_user_sub(Map<String, Object> params);
    
    public int set_reward(String memId);
    
    public int count_reward(Map<String, Object> params);
    
    public int getCash(String id);
    
    public List<Reward_GoodsDTO> getgoods();
}