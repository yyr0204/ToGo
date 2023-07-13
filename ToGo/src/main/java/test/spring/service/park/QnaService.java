package test.spring.service.park;

import java.util.List;

import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;

public interface QnaService {
	public void qnaInsert(QnaDTO dto);			//글 저장
	public List<QnaDTO> qnaList();				//목록 조회
	public QnaPage qnaList(QnaPage page);		//페이지 처리 된 공지글 목록 조회
	public QnaDTO qnaDetail(int num);			//상세 조회
	public void qnaUpdate(QnaDTO dto);			//글 수정
	public void qnaDelete(int no);				//글 삭제
	public void qnaRead(int no);				//조회수 증가 처리
	public void qnaReplyInsert(QnaDTO dto);		//답글 저장
}
