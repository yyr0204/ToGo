package test.spring.controller.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import test.spring.service.song.TripService;

@Controller
@RequestMapping("/trip/*")
public class TripController {

//	@Autowired
//	private TripService service;
	
	@RequestMapping("test")
	public String test() {
		
		return "/song/test";
	}
}