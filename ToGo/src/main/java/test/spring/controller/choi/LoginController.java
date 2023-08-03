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
	public @ResponseBody String login(KakaoDTO dto,String email, HttpSession session, Model model) {
		KakaoDTO user = mpservice.user_info2(dto.getId());
		System.out.println(dto);
		if(user != null) {
			String userEmail = user.getEmail();
			System.out.println(userEmail);
			if(userEmail.equals("sample")) {
				if(session.getAttribute("key")==null) {
					session.setAttribute("key", dto.getId());
				}
				return "notUser";
			}else {
				if((user.getStatus()!=null) && (user.getStatus().equals("N"))) {
					return "black";
				}else {
					return "main";
				}
			}
		}else {
			if(dto.getEmail() == null) {
				dto.setEmail("sample");
				ls.kakaoInsert(dto);
				session.setAttribute("key", dto.getId());
				return "notUser";
			}else {
				System.out.println(dto.getEmail());
				session.setAttribute("memId", dto.getEmail());
				ls.kakaoInsert(dto);
				return "question";
			}
		}
	}
	
	@RequestMapping("email")
	public String email(HttpSession session, Model model) {
		String id = (String)session.getAttribute("key");
		model.addAttribute("id", id);
		session.invalidate();
		return "/song/email";
	}
	
	@RequestMapping("emailPro")
	public String emailPro(String id, String email, HttpSession session, Model model) {
		int count = ls.check2(email);
		System.out.println(id);
		
		if(count == 0) {
			ls.addEmail(id, email);
			session.setAttribute("memId", email);
			return "redirect:/pwSetting";
		}else {
			model.addAttribute("id", id);
			model.addAttribute("re", 1);
			return "/song/email";
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

