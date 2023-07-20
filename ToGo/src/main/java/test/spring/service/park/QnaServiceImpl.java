package test.spring.service.park;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;
import test.spring.mapper.park.QnaMapper;

@Service("qnaservice")
public class QnaServiceImpl implements QnaService{
	@Autowired
	private QnaMapper mapper;

	@Override
	public void qnaInsert(QnaDTO dto) {
		mapper.qnaInsert(dto);
	}

	@Override
	public List<QnaDTO> qnaList() {
		return mapper.qnaList();
	}

	@Override
	public QnaPage qnaList(QnaPage page) {
	    page.setTotalList(mapper.totalList(page));
	    page.setList(mapper.list(page));
	    return page;
	}

	@Override
	public QnaDTO qnaDetail(int num) {
		return mapper.qnaDetail(num);
	}

	@Override
	public void qnaUpdate(QnaDTO dto) {
		mapper.qnaUpdate(dto);
	}

	@Override
	public void qnaDelete(int num) {
		mapper.qnaDelete(num);
	}

	@Override
	public void qnaRead(int num) {
		mapper.qnaRead(num);
	}

	@Override
	public void qnaReplyInsert(QnaDTO dto) {
		mapper.qnaReplyInsert(dto);
	}
}
