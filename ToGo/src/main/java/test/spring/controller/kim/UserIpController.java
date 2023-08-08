package test.spring.controller.kim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.kim.Admin_reward;
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
	
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> exchange(@RequestBody Map<String, String> pay) {

		String goodsId = pay.get("goodsId");
	    String memId = pay.get("memId");
	    String address = pay.get("address");
	    int points = Integer.parseInt(pay.get("points"));
	    
	    userservice.sub_point(points, memId);
	    userservice.add_goods(memId, address, goodsId);
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("success", true);

	    return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/Admin_reward")
	public String admin_reward(Model model, HttpServletRequest request,@RequestParam(value = "id", required = false) Long id) {
		System.out.println("번호"+id);
		List<Admin_reward> list = userservice.admin_reward();
		model.addAttribute("list", list);
		String status = request.getParameter("status");
		System.out.println("상태"+status);
		if(status!=null) {
			userservice.status_update(status, id);
		}
		return "/kim/admin_reward";
	}
	@RequestMapping("/test")
	public @ResponseBody String test01(Map<String,String>status) {
		System.out.println();
		return null;
	}
}
