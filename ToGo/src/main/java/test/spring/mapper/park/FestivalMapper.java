package test.spring.mapper.park;

import java.util.List;


import test.spring.component.park.FstvlDTO;
public interface FestivalMapper {
	public List<FstvlDTO> fstvl(FstvlDTO dto);
	public List<FstvlDTO> fstvlList(FstvlDTO dto);
	// �Խñ��� ������ ��ȸ
	public int selectFstvlCount(FstvlDTO dto);

}
