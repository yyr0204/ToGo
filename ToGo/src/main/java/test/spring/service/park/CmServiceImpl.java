package test.spring.service.park;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.park.CmBoardDTO;
import test.spring.mapper.park.CmMapper;
@Service("cmservice")
public class CmServiceImpl implements CmService{
	@Autowired
	private CmMapper mapper;
	@Override
	public int addBoard(CmBoardDTO dto) {
	    if (dto.getDepth() == null || dto.getDepth() == 0) {
	        // 본문 작성
	        dto.setDepth((long) 1);
	        dto.setStep((long) 0);
	        Long max = mapper.selectBoardMax();
	        long cm_group = max != null ? max + 1 : 1;
	        dto.setCm_group(cm_group);

	        return mapper.insertBoard(dto);
	    } else if (dto.getDepth() == 3) {
	        // 대댓글 작성
	        dto.setCm_title("reComment");
	        return mapper.insertBoard(dto);
	    } else {
	        // 댓글 작성
	        Long maxC = mapper.selectCommentMax(dto);
	        long step = maxC != null ? maxC + 1 : 1;
	        dto.setStep(step);
	        dto.setCm_title("comment");
	        return mapper.insertBoard(dto);
	    }
	}

	@Override
	public CmBoardDTO getBoardDetail(Long cm_no) {
		return mapper.selectBoardDetail(cm_no);
	}

	@Override
	public int modifyBoard(CmBoardDTO dto) {
		return mapper.updateBoard(dto);
	}

	@Override
	public int deleteBoard(CmBoardDTO dto) {
		return mapper.deleteBoard(dto);
	}

	@Override
	public List<CmBoardDTO> getBoardList(CmBoardDTO dto) {
		return mapper.selectBoardList(dto);
	}

	@Override
	public int selectBoardTotalCount(CmBoardDTO dto) {
		return mapper.selectBoardTotalCount(dto);
	}

	@Override
	public List<CmBoardDTO> getCommentList(CmBoardDTO dto) {
		return mapper.selectCommentList(dto);
	}

	@Override
	public List<CmBoardDTO> getMypost(CmBoardDTO dto) {
		return mapper.selectMypost(dto);
	}

	@Override
	public int selectMyPostTotalCount(CmBoardDTO dto) {
		return mapper.selectMyPostTotalCount(dto);
	}

	@Override
	public int commentCnt(Long cm_no) {
		return mapper.commentCnt(cm_no);
	}

	@Override
	public int updatereadcnt(Long cm_no) {
		return mapper.updatereadcnt(cm_no);
	}
}
