package test.spring.mapper.kim;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.kim.Admin_reward;
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
    
    public int sub_point(@Param("points")int points, @Param("memId")String memId);
    
    public int add_goods(@Param("memId")String memId, @Param("address")String address, @Param("goodsId")String goodsId);
    
    public List<Admin_reward> admin_reward();
    
    public int status_update(@Param("status")String status, @Param("id")Long id);
    
    public List<kimDTO> mainCourseInfo(@Param("plan_num")String plan_num);
}