package test.spring.service.park;

import java.util.Map;

public interface QuestionService {
	public void saveResult(String result,String id);
	public Map<String, String> processQuestion(String questionId, String answer);
}
