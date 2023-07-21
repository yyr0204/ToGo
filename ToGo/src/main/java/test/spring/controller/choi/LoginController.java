package test.spring.controller.choi;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.component.choi.KakaoDTO;
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
		public String login(KakaoDTO dto,String id,HttpSession session) {
			// 로그인 정보 세션에 저장
		    session.setAttribute("memId", id);
		    String memId = (String) session.getAttribute("memId");
		    System.out.println("세션 :"+memId);
			int count = ls.check(id);
				if(count == 0) {
					ls.kakaoInsert(dto);
				}else {
					return "/choi/loginMain";
				}
			return "/choi/loginMain";
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

