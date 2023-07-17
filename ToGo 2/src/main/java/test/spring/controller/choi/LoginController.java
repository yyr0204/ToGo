package test.spring.controller.choi;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.choi.LoginDTO;
import test.spring.service.choi.LoginService;
import test.spring.service.choi.TestService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	public LoginService ls;
	
	@Autowired
	public TestService testService;
	
	@Autowired
	private HttpSession session;
	
		
		
		@RequestMapping("login")
		public String login(KakaoDTO dto,String id) {
			int count = ls.check(id);
				if(count == 0) {
					ls.kakaoInsert(dto);
				}
			return "/choi/login";
		}
		@RequestMapping("loginMain")
		public String loginMain() {
			return"/choi/loginMain";
		}
}
		
	
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
		
		
		
//		@RequestMapping(value="/logout")
//	    public String logout(HttpSession session) {
//	        String access_Token = (String)session.getAttribute("access_Token");
//
//	        if(access_Token != null && !"".equals(access_Token)){
//	        	ls.getAccessToken(access_Token);
//	            session.removeAttribute("access_Token");
//	            session.removeAttribute("userId");
//	        }else{
//	            System.out.println("access_Token is null");
//	            System.out.println(access_Token);
//	            //return "redirect:/";
//	        }
//	        //return "index";
//	        return "/choi/loginMain";
//	    }
//	
//	}

