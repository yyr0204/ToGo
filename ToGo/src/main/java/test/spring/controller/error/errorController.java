package test.spring.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class errorController {

	
	@RequestMapping("/error404")
	public String error404() {
		return "/error/error404";
	}
	@RequestMapping("/error500")
	public String error500() {
		return "/error/error500";
	}
}