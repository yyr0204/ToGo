package test.spring.controller.park;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.component.choi.KakaoDTO;
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
	@RequestMapping("/user_check")
	public String user_check() {
		return "/park/myPage/user_check";
	}
	@RequestMapping("/modifyForm")
	public String modifyForm(String id, String pw, HttpSession session, Model model,KakaoDTO dto) {
		id = (String) session.getAttribute("memId");
		dto = mpservice.user_info(id, pw);
		model.addAttribute("dto",dto);
		return "/park/myPage/modifyForm";
	}
	@RequestMapping("/modifyPro")
	public String modifyPro(HttpSession session,KakaoDTO dto,Model model) {
		String id = (String)session.getAttribute("memId");
		dto.setId(id);
		int uPTrue = mpservice.update_info(dto);
		model.addAttribute("uPTrue",uPTrue);
		return "redirect:/trip/main";
	}
	
}
