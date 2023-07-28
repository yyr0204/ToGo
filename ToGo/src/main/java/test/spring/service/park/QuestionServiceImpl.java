package test.spring.service.park;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.mapper.park.QuestionMapper;

@Service("questionservice")
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionMapper mapper;
	
	public void saveResult(String result,String id) {
		mapper.saveResult(result,id);
    }
    @Override
    public Map<String, String> processQuestion(String questionId, String answer) {
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

        return response;
    }

}
