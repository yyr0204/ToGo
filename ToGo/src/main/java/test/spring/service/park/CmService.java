package test.spring.service.park;

import java.util.List;

import test.spring.component.park.CmBoardDTO;

public interface CmService {
	// 게시물 등록
		public int addBoard(CmBoardDTO dto);
		// 게시물 조회 
		public CmBoardDTO getBoardDetail(Long cm_no);
		// 게시물 수정 
		public int modifyBoard(CmBoardDTO dto);
		// 게시물 삭제 
		public int deleteBoard(CmBoardDTO dto);
		// 게시물 목록 조회 
		public List<CmBoardDTO> getBoardList(CmBoardDTO dto);
		// 게시물 개수
		public int selectBoardTotalCount(CmBoardDTO dto);
		// 댓글 리스트 
		public List<CmBoardDTO> getCommentList(CmBoardDTO dto);
		// 내가 쓴 글 조회 
		public List<CmBoardDTO> getMypost(CmBoardDTO dto);
		// 내가 쓴 글의 개수를 조회 
		public int selectMyPostTotalCount(CmBoardDTO dto);
		// 댓글 수 조회 
		public int commentCnt(Long cm_no);
		// 게시글 조회수 증가
		public int updatereadcnt(Long cm_no);
}
