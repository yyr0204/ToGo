package test.spring.controller.choi;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.choi.KakaoDTO;
import test.spring.repository.song.KakaoDAO;
import test.spring.service.choi.LoginService;
import test.spring.service.choi.TestService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	public LoginService ls;
	
	@Autowired
	public TestService testService;
	
	@RequestMapping("login")
	public @ResponseBody String login(KakaoDTO dto,String id, HttpSession session, Model model) {
		int count = ls.check(dto.getId());
		
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
	
	/*
	@RequestMapping("inputCheck")
	public String inputCheck(Model model,HttpSession session) {
		System.out.println("test");
		String memId = (String) session.getAttribute("memId");
		System.out.println("memId2 : " + memId);
		if(memId != null) {
			String mbti = ls.mbtiCheck(memId);
			
			if(mbti != "A" && mbti != "B" && mbti != "C") {
				return "/park/question";
			}else {
				return "redirect:/trip/main";
			}
		}
		System.out.println("memId : " + memId);
		return "/choi/loginMain";
	}
	*/
	
//		@RequestMapping(value="/kakaologin", method=RequestMethod.GET)
//		public String kakaoLogin(@RequestParam(value = "code", required = false) String code)  {
//			String access_Token = ls.getAccessToken(code);
//			HashMap<String, String> userInfo = ls.getUserInfo(access_Token);
//			String nick = userInfo.get("nickname");
//			String email = userInfo.get("email");
//			
//			ls.kakaoInsert(nick,email);
//			return "/choi/loginMain";
//	    	}
		
	
}

