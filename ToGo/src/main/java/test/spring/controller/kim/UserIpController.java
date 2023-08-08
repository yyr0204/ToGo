package test.spring.controller.kim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.kim.CityAndPlaces;
import test.spring.component.kim.Pos;
import test.spring.component.kim.Reward_GoodsDTO;
import test.spring.component.kim.kimDTO;
import test.spring.repository.song.HaversineDAO;
import test.spring.service.kim.UserIpService;

@Controller
@RequestMapping("/User/*")
public class UserIpController {
	
	@Autowired
	private UserIpService userservice;
	
	@RequestMapping("userIp")
	public String test() {
		return "/kim/userIp";
	}
	
	@RequestMapping("reco_place")
	public String test1(Model model) {

		return "/kim/userIp";
	}
	
	@RequestMapping(value = "/user_ip", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<CityAndPlaces> getCityAndPlaces(@RequestBody Map<String, Object> data) {
	    double lat = (double)data.get("lat");
	    double lng = (double)data.get("lng");
	    boolean isMain = (boolean)data.get("isMain");
	    boolean isSub = (boolean)data.get("isSub");
	    
	    Pos pos = new Pos(lat, lng);
	    ArrayList<CityAndPlaces> places= userservice.getCityAndPlaces(pos, isMain, isSub);
	    
	    return places;
	}
	
	@RequestMapping("reward")
	public String reward() {
		return "/kim/rewardIp";
	}
	
	@RequestMapping(value = "/reward_ip", method = RequestMethod.POST)
	@ResponseBody
	public String rewardIp(@RequestBody Map<String, Object> location) {
	    double lat = (double) location.get("lat");
	    double lng = (double) location.get("lng");
	    String memId = (String) location.get("memId"); 
	    
	    Pos pos = new Pos(lat, lng); // Creating Pos object
	    Map<String, Object> params = userservice.getParams(pos); // Pass Pos object to the service
	    
	    int count = userservice.count_reward(params);
	    
	    System.out.println(pos);
	    System.out.println(params);
	    System.out.println(memId);
	    System.out.println(count);
	    
	    if(count != 0) {
	        userservice.set_reward(memId);
	        return "success";
	    }
	    return "fail";
	}
	
	@RequestMapping("reward_shop")
	public String reward_shop(HttpSession session, Model model) {
		String id = (String) session.getAttribute("memId");
		int cash = userservice.getCash(id);
		List<Reward_GoodsDTO> goods = userservice.getgoods();
		model.addAttribute("cash", cash);
		model.addAttribute("goods", goods);
		return "/kim/reward_shop";
	}

}
