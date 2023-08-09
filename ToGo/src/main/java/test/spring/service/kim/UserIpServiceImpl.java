package test.spring.service.kim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.kim.Admin_reward;
import test.spring.component.kim.CityAndPlaces;
import test.spring.component.kim.Pos;
import test.spring.component.kim.RewardDTO;
import test.spring.component.kim.Reward_GoodsDTO;
import test.spring.component.kim.kimDTO;
import test.spring.mapper.kim.UserIp;
import test.spring.repository.song.HaversineDAO;
@Service
public class UserIpServiceImpl implements UserIpService{

    @Autowired
    private UserIp mapper;
    
    @Override
    public List<kimDTO> reco_place() {
        return mapper.reco_place();
    }
    
    @Override
    public ArrayList<CityAndPlaces> getCityAndPlaces(Pos pos, boolean isMain, boolean isSub){
        HaversineDAO dao = new HaversineDAO();
       
        List LatLon = dao.radius(pos.getLat(),pos.getLng(),1);
        double minLat = (double)LatLon.get(0);
        double maxLat = (double)LatLon.get(1);
        double minLon = (double)LatLon.get(2);
        double maxLon = (double)LatLon.get(3);
        
        Map<String, Object> params = new HashMap<>();
        params.put("minLat", minLat);
        params.put("maxLat", maxLat);
        params.put("minLon", minLon);
        params.put("maxLon", maxLon);
        
        String city;
        
        if ((37.413294 <= pos.getLat() && pos.getLat() <= 37.715133) &&
                (126.734086 <= pos.getLng() && pos.getLng() <= 127.269311)) {
                city = "seoul";
        } else {    
                city = "daejeon";
        }
        
        CityAndPlaces result = new CityAndPlaces();
        result.setCity(city);
        
        ArrayList<CityAndPlaces> places = new ArrayList<>();
        
        if (isMain) {
            params.put("table", city + "_main");
            List<kimDTO> mainPlaces = mapper.reco_place_user_main(params);
            result.setMainPlaces(mainPlaces);
            result.setPlaces(mainPlaces);
            places.add(result);
        }
        if (isSub) {
            params.put("table", city + "_sub");
            List<kimDTO> subPlaces = mapper.reco_place_user_sub(params);
            result.setSubPlaces(subPlaces);
            result.setPlaces(subPlaces);
            places.add(result);
        }
        return places;
    }
    
    @Override
    public List<kimDTO> reco_place_user_main(Map<String, Object> params){
        return mapper.reco_place_user_main(params);
    }

    @Override
    public List<kimDTO> reco_place_user_sub(Map<String, Object> params){
        return mapper.reco_place_user_sub(params);
    }
    
    @Override
    public int set_reward(String memId) {
        return mapper.set_reward(memId);
    }

    @Override
    public int count_reward(Map<String, Object> params) {
        return mapper.count_reward(params);
    }

    @Override
    public Map<String, Object> getParams(Pos pos) {
        HaversineDAO dao = new HaversineDAO();
           
        List<Double> LatLon = dao.radius(pos.getLat(), pos.getLng(), 0.5);
        Map<String, Object> params = new HashMap<>();
        params.put("minLat", LatLon.get(0));
        params.put("maxLat", LatLon.get(1));
        params.put("minLon", LatLon.get(2));
        params.put("maxLon", LatLon.get(3));
        return params;
    }
    
    @Override
    public int getCash(String id) {
    	return mapper.getCash(id);
    }
    
    @Override
    public List<Reward_GoodsDTO> getgoods() {
        return mapper.getgoods();
    }
    
    @Override
    public int sub_point(int points, String memId) {
    	return mapper.sub_point(points, memId);
    }
    
    @Override
    public int add_goods(String memId, String address, String goodsId) {
    	return mapper.add_goods(memId, address, goodsId);
    }
    
    @Override
    public List<Admin_reward> admin_reward(){
    	return mapper.admin_reward();
    }
    
    @Override
    public int status_update(String status, Long id) {
    	return mapper.status_update(status, id);
    }

	@Override
	public List<kimDTO> mainCourseInfo(String plan_num) {
		return mapper.mainCourseInfo(plan_num);
	}

	@Override
	public void chTourStatus(String plan_num) {
		mapper.chTourStatus(plan_num);
	}

}
