package test.spring.controller.park;

import java.util.HashMap;
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

import test.spring.service.park.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionservice;
	
    @GetMapping("/question")
    public String showQuestionPage(@RequestParam(name = "questionId", defaultValue = "1") String questionId, Model model) {
        model.addAttribute("questionId", questionId);
        return "park/question";
    }
    @RequestMapping("/save-result")
    public String saveResult(@RequestParam("result") String result,String id,HttpSession session) {
    	// db의 id와 세션의 이메일이 같으면 결과를 넣습니다.
    	id = (String)session.getAttribute("k_email");
    	// 결과를 데이터베이스에 저장하는 로직을 수행합니다.
    	questionservice.saveResult(result,id);
        return "/loginMain";
    }

    @PostMapping("/question")
    public ResponseEntity<Map<String, String>> processQuestion(@RequestParam("questionId") String questionId, @RequestParam("answer") String answer) {
        String nextQuestionId = null;
        String result = null;
        
        switch (questionId) {
            case "1":
                nextQuestionId = answer.equals("yes") ? "2-2" : "2-1";
                break;
            case "2-1":
                nextQuestionId = answer.equals("yes") ? "3-2" : "3-1";
                break;
            case "2-2":
                nextQuestionId = answer.equals("yes") ? "3-3" : "3-2";
                break;
            case "3-1":
                nextQuestionId = answer.equals("yes") ? "4-1" : "4-3";
                break;
            case "3-2":
                nextQuestionId = answer.equals("yes") ? "3-3" : "3-1";
                break;
            case "3-3":
                nextQuestionId = answer.equals("yes") ? "4-3" : "4-4";
                break;
            case "4-1":
                if (answer.equals("yes")) {
                    result = "A";
                } else {
                    nextQuestionId = "4-2";
                }
                break;
            case "4-2":
                nextQuestionId = answer.equals("yes") ? "5-1" : "5-2";
                break;
            case "4-3":
                if (answer.equals("yes")) {
                    result = "B";
                } else {
                    nextQuestionId = "5-2";
                }
                break;
            case "4-4":
                if (answer.equals("yes")) {
                    result = "C";
                } else {
                    result = "B";
                }
                break;
            case "5-1":
                if (answer.equals("yes")) {
                    result = "B";
                } else {
                    result = "A";
                }
                break;
            case "5-2":
                if (answer.equals("yes")) {
                    result = "C";
                } else {
                    result = "A";
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid questionId");
        }

        Map<String, String> response = new HashMap<>();
        response.put("nextQuestionId", nextQuestionId);
        response.put("result", result);

        return ResponseEntity.ok(response);
    }
}
