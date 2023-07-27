package test.spring.controller.choi;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.choi.KakaoDTO;
import test.spring.service.choi.LoginService;
import test.spring.service.choi.TestService;
import test.spring.service.park.MyPageService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	public LoginService ls;
	@Autowired
	public MyPageService mpservice;
	
	@Autowired
	public TestService testService;
	
	@RequestMapping("login")
	public @ResponseBody String login(KakaoDTO dto,String id, HttpSession session, Model model) {
		int count = ls.check(dto.getId());
		dto = mpservice.user_info(id);
		if(dto.getStatus().equals("N")) {
			return "black";
		}
		session.setAttribute("memId", dto.getId());
		if(count == 0) {
			ls.kakaoInsert(dto);
			return "question";
		}else {
			return "main";
		}
	}
	
	@RequestMapping("loginMain")
	public String loginMain(Model model,HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("memid", memId);
		return"/choi/loginMain";
	}
	
	@RequestMapping("logout")
    public String logout(HttpSession session) {
		
		session.invalidate();
		
        return "/song/logout";
    }
	@RequestMapping("admlogin")
	public String admlogin(String id,String pw,HttpSession session,Model model) {
		int count = ls.adminCheck(id, pw);
		if(count==1) {
			session.setAttribute("adminId", id);
			session.setAttribute("level", "3");
			
			return"redirect:/trip/main";
		}else {
			model.addAttribute("result",count);
			return "/choi/loginMain";
		}
	}
}

