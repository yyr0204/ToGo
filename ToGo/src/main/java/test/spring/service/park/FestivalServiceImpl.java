package test.spring.service.park;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.park.FstvlDTO;
import test.spring.mapper.park.FestivalMapper;
@Service("festivalService")
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
    public List<FstvlDTO> getRandomFstvlList(FstvlDTO dto) {
        List<FstvlDTO> fstvlList = mapper.fstvl(dto);

        List<FstvlDTO> randomFstvlList = new ArrayList<>();
        if (fstvlList.size() > 5) {
            List<Integer> indexes = new ArrayList<>();
            for (int i = 0; i < fstvlList.size(); i++) {
                indexes.add(i);
            }
            Collections.shuffle(indexes);

            for (int i = 0; i < 5; i++) {
                randomFstvlList.add(fstvlList.get(indexes.get(i)));
            }
        } else {
            randomFstvlList = fstvlList;
        }

        return randomFstvlList;
    }

}
