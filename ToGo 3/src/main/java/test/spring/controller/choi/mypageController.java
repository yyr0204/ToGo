package test.spring.controller.choi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mypageController {
	 @GetMapping("/mypageMain")
	    public String mypageMain(@RequestParam(name = "questionId", defaultValue = "1") String questionId, Model model) {
	        model.addAttribute("questionId", questionId);
	        return "park/question";
	    }
}
