package test.spring.controller.park;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

	@GetMapping("/question")
	public String showQuestionPage() {
		return "park/question";
	}

	@PostMapping("/question")
	public ResponseEntity<Map<String, String>> processQuestion(@RequestParam String questionId,
			@RequestParam String answer) {
		String nextQuestionId;
		String result = null;
		try {
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
					nextQuestionId = null;
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
					nextQuestionId = null;
				} else {
					nextQuestionId = "5-2";
				}
				break;
			case "4-4":
				if (answer.equals("yes")) {
					result = "C";
					nextQuestionId = null;
				} else {
					result = "B";
					nextQuestionId = null;
				}
				break;
			case "5-1":
				if (answer.equals("yes")) {
					result = "B";
				} else {
					result = "A";
				}
				nextQuestionId = null;
				break;
			case "5-2":
				if (answer.equals("yes")) {
					result = "C";
				} else {
					result = "A";
				}
				nextQuestionId = null;
				break;
			default:
				throw new IllegalArgumentException("Invalid questionId");
			}

			Map<String, String> response = new HashMap<>();
			response.put("nextQuestionId", nextQuestionId);
			response.put("result", result);

			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
