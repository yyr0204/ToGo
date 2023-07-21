package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.park.QnaDTO;
@Mapper
public interface QnaMapper {
	public void qnaInsert(QnaDTO dto);			//글 저장
	public List<QnaDTO> qnaList(QnaDTO dto);	//목록 조회
	public QnaDTO qnaDetail(int num);			//상세 조회
	public void qnaUpdate(QnaDTO dto);			//글 수정
	public void qnaDelete(int num);				//글 삭제
	public void qnaRead(int num);				//조회수 증가 처리
	public void qnaReplyInsert(QnaDTO dto);		//답글 저장
	public int totalList(QnaDTO dto);
}
