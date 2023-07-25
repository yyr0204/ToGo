package test.spring.controller.park;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.choi.KakaoDTO;
import test.spring.service.choi.LoginService;
import test.spring.service.park.MyPageService;
import test.spring.service.park.QuestionService;

@Controller
public class UserInfoController {
	@Autowired
	private QuestionService questionservice;
	@Autowired
	private LoginService ls;
	@Autowired
	private MyPageService mpservice;
	
	@RequestMapping("/pwSetting")
	public String pwSetting() {
		return "park/pwSetting";
	}
	@RequestMapping("/pwSettingPro")
	public String pwSettingPro(String pw, String id,HttpSession session) {
		id = (String) session.getAttribute("memId");
		System.out.println("pw :"+pw);
		System.out.println("id :"+id);
		ls.pwSetting(pw,id);
		return "redirect:/question";
	}
    @GetMapping("/question")
    public String showQuestionPage(@RequestParam(name = "questionId", defaultValue = "1") String questionId, Model model) {
        model.addAttribute("questionId", questionId);
        return "park/question";
    }
    @RequestMapping("/save-result")
    public @ResponseBody String saveResult(@RequestParam("result") String result,@RequestParam("id") String id,HttpSession session) {
    System.out.println("성향 :"+result);
    System.out.println("아이디 :"+id);	
    	questionservice.saveResult(result,id);
        return "ok";
    }
    @PostMapping("/question")
    public ResponseEntity<Map<String, String>> processQuestion(@RequestParam("questionId") String questionId, @RequestParam("answer") String answer, String id) {
        Map<String, String> response = questionservice.processQuestion(questionId, answer);
        return ResponseEntity.ok(response);
    }
    // user info modify-----------------------------------
    @RequestMapping("/myPage/myPage")
	public String myPage() {
		return "/park/myPage/myPageMain";
	}
	@RequestMapping("/myPage/user_check")
	public String user_check() {
		return "/park/myPage/user_check";
	}
	@RequestMapping("/myPage/modifyForm")
	public String modifyForm(String id, String pw, HttpSession session, Model model,KakaoDTO dto) {
		id = (String) session.getAttribute("memId");
		dto = mpservice.user_info(id, pw);
		model.addAttribute("dto",dto);
		return "/park/myPage/modifyForm";
	}
	@RequestMapping("/myPage/modifyPro")
	public String modifyPro(HttpSession session,KakaoDTO dto,Model model) {
		String id = (String)session.getAttribute("memId");
		dto.setId(id);
		int uPTrue = mpservice.update_info(dto);
		model.addAttribute("uPTrue",uPTrue);
		return "redirect:/trip/main";
	}
}
