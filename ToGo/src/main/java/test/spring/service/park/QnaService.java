package test.spring.service.park;

import java.util.List;

import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;

public interface QnaService {
	public void qnaInsert(QnaDTO dto);			
	public List<QnaDTO> qnaList(QnaDTO dto);	//목록 조회
	public QnaDTO qnaDetail(int num);			
	public void qnaUpdate(QnaDTO dto);			
	public void qnaDelete(int num);				
	public void qnaRead(int num);				
	public void qnaReplyInsert(QnaDTO dto);		
	public int totalList(QnaDTO dto);
}
