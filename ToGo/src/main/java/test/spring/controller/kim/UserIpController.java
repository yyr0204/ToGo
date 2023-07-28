package test.spring.controller.kim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
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
}
