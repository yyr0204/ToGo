package test.spring.service.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.spring.component.park.FstvlDTO;
import test.spring.mapper.park.FestivalMapper;

import java.util.List;
@Service
public class FestivalServiceImpl implements FestivalService {

    @Autowired
    private FestivalMapper mapper;
    @Override
    public List<FstvlDTO> fstvlList(FstvlDTO dto) {
        return mapper.fstvlList(dto);
    }
	@Override
	public int selectFstvlCount(FstvlDTO dto) {
		return mapper.selectFstvlCount(dto);
	}
	@Override
	public List<FstvlDTO> fstvl(FstvlDTO dto) {
		return mapper.fstvl(dto);
	}

}
