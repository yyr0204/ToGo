package test.spring.controller.park;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.park.CmBoardDTO;
import test.spring.component.park.PageResolver;
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
    	questionservice.saveResult(result,id);
        return "ok";
    }
    @PostMapping("/question")
    public ResponseEntity<Map<String, String>> processQuestion(@RequestParam("questionId") String questionId, @RequestParam("answer") String answer, String id) {
        Map<String, String> response = questionservice.processQuestion(questionId, answer);
        return ResponseEntity.ok(response);
    }
    // user info modify-----------------------------------
	@RequestMapping("/myPage/user_check")
	public String user_check() {
		return "/park/myPage/user_check";
	}
	@RequestMapping("/myPage/modifyForm")
	public String modifyForm(String id,String pw , HttpSession session, Model model,KakaoDTO dto) {
		try {
			id = (String) session.getAttribute("memId");
			dto = mpservice.user_info(id);
			if(pw.equals(dto.getPw())) {
				model.addAttribute("dto",dto);
				return "/park/myPage/modifyForm";
			}else {
				model.addAttribute("errorMessage", "비밀번호가 틀렸습니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/park/myPage/user_check";
	}
	@RequestMapping("/myPage/modifyPro")
	public String modifyPro(HttpSession session,KakaoDTO dto,Model model,MultipartFile save, HttpServletRequest request) {
		String uploadDirectory = request.getRealPath("/resources/static/profile");
		String fileName = save.getOriginalFilename();   //파일 이름
		String filePath = uploadDirectory +File.separator+fileName;   //파일 경로+ 파일 이름
		if (!save.isEmpty()) {
	        File file = new File(filePath);
	        if (file.exists()) {
	            // 파일이 이미 존재하면 삭제
	            file.delete();
	        }

	        try {
	            save.transferTo(file);
	            dto.setProfile_img(fileName);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		String id = (String)session.getAttribute("memId");
		dto.setId(id);
		int uPTrue = mpservice.update_info(dto);
		model.addAttribute("uPTrue",uPTrue);
		return "forward:/trip/main";
	}
	@RequestMapping("/admin/userManagement")
	public String userList(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model,KakaoDTO dto, String option, String keyword) {
		// 검색조건
		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		// 페이지네이션
		int pageSize = 10; // 페이지 당 게시글 갯수
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// 리스트 총 갯수
		int total = mpservice.userCount(dto);
		// 첫 글 번호
		int beginPage = (page - 1) * pageSize + 1;
		// 마지막 글 번호
		int endPage = beginPage + pageSize - 1;
		dto.setBeginPage(beginPage);
		dto.setEndPage(endPage);

		List<KakaoDTO> userlist = mpservice.userList(dto);
		PageResolver pr = new PageResolver(page, pageSize, total);
		model.addAttribute("userlist", userlist);
		model.addAttribute("pr", pr);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);
		return "/park/userManagement";
	}
	@RequestMapping("/admin/chStatus")
	public String chStatus(String id, String status, Model model) {
		String statusUser = status.equals("N")?"Y":"N";
		int chStatus = mpservice.chStatus(id, statusUser);
		model.addAttribute("chStatus",chStatus);
		return "redirect:/admin/userManagement";
	}
}
