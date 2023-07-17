package test.spring.service.park;

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
}
