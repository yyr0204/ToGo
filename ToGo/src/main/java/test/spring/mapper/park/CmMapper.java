package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.park.CmBoardDTO;
@Mapper
public interface CmMapper {
	// 최근 게시물 번호
	public Long selectBoardMax();
	// 최근 댓글 번호
	public Long selectCommentMax(CmBoardDTO dto);
	// 게시물 등록
	public int insertBoard(CmBoardDTO dto);
	// 게시물 한개 조회
	public CmBoardDTO selectBoardDetail(Long cm_no);
	// 게시물 목록 조회
	public List<CmBoardDTO> selectBoardList(CmBoardDTO dto);
	// 댓글 리스트
	public List<CmBoardDTO> selectCommentList(CmBoardDTO dto);
	// 게시글의 개수를 조회
	public int selectBoardTotalCount(CmBoardDTO dto);
	// 게시물 수정
	public int updateBoard(CmBoardDTO dto);
	// 게시물 삭제
	public int deleteBoard(CmBoardDTO dto);
	// 내가 쓴 글 조회
	public List<CmBoardDTO> selectMypost(CmBoardDTO dto);
	// 내가 쓴 글의 개수를 조회 CmBoardDTO
	public int selectMyPostTotalCount(CmBoardDTO dto);
	// 댓글 수 조회
	public int commentCnt(Long cm_no);
	// 게시물 조회수 증가
	public int updatereadcnt(Long cm_no);
}
