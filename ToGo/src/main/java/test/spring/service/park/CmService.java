package test.spring.service.park;

import java.util.Date;
import java.util.List;

import test.spring.component.park.CmBoardDTO;

public interface CmService {
	public int addBoard(CmBoardDTO dto);
	public CmBoardDTO getBoardDetail(Long cm_no);
	public int modifyBoard(CmBoardDTO dto);
	public int deleteBoard(CmBoardDTO dto);
	public List<CmBoardDTO> getBoardList(CmBoardDTO dto);
	public List<CmBoardDTO> getCommentList(CmBoardDTO dto);
	public int selectMyPostTotalCount(CmBoardDTO dto);
	public int commentCnt(Long cm_no);
	public int updatereadcnt(Long cm_no);
	public int selectBoardTotalCount(CmBoardDTO dto);
	public List<CmBoardDTO> getMypost(CmBoardDTO dto);
	public int set_reward(String memId);
	public int check_date(String strDate,String memId);
}
