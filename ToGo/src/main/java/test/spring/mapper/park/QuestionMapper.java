package test.spring.mapper.park;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface QuestionMapper {
	public void saveResult(@Param("result")String result,@Param("id") String id);
}
