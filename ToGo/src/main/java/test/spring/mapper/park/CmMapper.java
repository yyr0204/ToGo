package test.spring.mapper.park;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import test.spring.component.park.CmBoardDTO;
@Mapper
public interface CmMapper {
	// �ֱ� �Խù� ��ȣ
	public Long selectBoardMax();
	// �ֱ� ��� ��ȣ
	public Long selectCommentMax(CmBoardDTO dto);
	// �Խù� ���
	public int insertBoard(CmBoardDTO dto);
	// �Խù� �Ѱ� ��ȸ
	public CmBoardDTO selectBoardDetail(Long cm_no);
	// �Խù� ��� ��ȸ
	public List<CmBoardDTO> selectBoardList(CmBoardDTO dto);
	public List<CmBoardDTO> selectCommentList(CmBoardDTO dto); // 댓글 조회
	// �Խñ��� ������ ��ȸ
	public int selectBoardTotalCount(CmBoardDTO dto);
	// �Խù� ����
	public int updateBoard(CmBoardDTO dto);
	// �Խù� ����
	public int deleteBoard(CmBoardDTO dto);
	// ���� �� �� ��ȸ
	public List<CmBoardDTO> selectMypost(CmBoardDTO dto);
	// ���� �� ���� ������ ��ȸ CmBoardDTO
	public int selectMyPostTotalCount(CmBoardDTO dto);
	// ��� �� ��ȸ
	public int commentCnt(Long cm_no);
	// �Խù� ��ȸ�� ����
	public int updatereadcnt(Long cm_no);
	
	public int set_reward(String memId);
	
	public int check_date(@Param("date") String date,@Param("memId") String memId);
}
