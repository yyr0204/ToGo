package test.spring.controller.choi;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	private HttpSession se;
		
		@RequestMapping("login")
		public String login() {
			return "/choi/login";
		}
//		@RequestMapping("insert")
//		public String insert(LoginDTO dto,Model model) {
//			service.insert(dto);
//			return "/choi/login";
//		}
//		@Autowired
//		private LoginService service;

//		@Controller
//		@RequestMapping(value="/choi/*")
//		public class LoginController {

			
//			@RequestMapping(/login)
//			public String login{
//				return "/choi/login";
//			}
			

		@RequestMapping(value="/kakaologin", method=RequestMethod.GET)
		public String kakaologin(@RequestParam(value = "code", required = false) String code){
			System.out.println("코드코드코" + code);
			String access_Token = ls.getAccessToken(code);
			KakaoDTO userInfo = ls.getUserInfo(access_Token);
			System.out.println("###access_Token#### : " + access_Token);
			System.out.println("###nickname#### : " + userInfo.getK_name());
			se.invalidate();
			// 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.
			se.setAttribute("kakaoN", userInfo.getK_name());
			se.setAttribute("kakaoE", userInfo.getK_email());
			// 위 2개의 코드는 닉네임과 이메일을 session객체에 담는 코드
			// jsp에서 ${sessionScope.kakaoN} 이런 형식으로 사용할 수 있다.
		    
		    // 리턴값은 용도에 맞게 변경하세요~
			return "choi/login";
		}
		
		}
