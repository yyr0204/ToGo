package test.spring.service.park;

import java.util.Date;
import java.util.List;

import test.spring.component.park.CmBoardDTO;

public interface CmService {
	// �Խù� ���
		public int addBoard(CmBoardDTO dto);
		// �Խù� ��ȸ 
		public CmBoardDTO getBoardDetail(Long cm_no);
		// �Խù� ���� 
		public int modifyBoard(CmBoardDTO dto);
		// �Խù� ���� 
		public int deleteBoard(CmBoardDTO dto);
		// �Խù� ��� ��ȸ 
		public List<CmBoardDTO> getBoardList(CmBoardDTO dto);
		// �Խù� ����
		public int selectBoardTotalCount(CmBoardDTO dto);
		// ��� ����Ʈ 
		public List<CmBoardDTO> getCommentList(CmBoardDTO dto);
		// ���� �� �� ��ȸ 
		public List<CmBoardDTO> getMypost(CmBoardDTO dto);
		// ���� �� ���� ������ ��ȸ 
		public int selectMyPostTotalCount(CmBoardDTO dto);
		// ��� �� ��ȸ 
		public int commentCnt(Long cm_no);
		// �Խñ� ��ȸ�� ����
		public int updatereadcnt(Long cm_no);
		
		public int set_reward(String memId);
		
		public int check_date(String strDate,String memId);
}
