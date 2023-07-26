package test.spring.mapper.kim;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.kim.kimDTO;

@Mapper
public interface UserIp {
	public List<kimDTO> reco_place();
}
