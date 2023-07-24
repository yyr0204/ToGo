package test.spring.controller.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.service.park.MyPageService;

@Controller
@RequestMapping("/myPage/*")
public class MyPageController {
	@Autowired
	MyPageService mpservice;
	
	@RequestMapping("/myPage")
	public String myPage() {
		return "/park/myPage/myPageMain";
	}
	@RequestMapping("/modifyForm")
	public String modifyForm() {
		
		return "/park/myPage/modifyForm";
	}
	
}
