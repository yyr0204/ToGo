package test.spring.service.song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.song.ImageBoard1DTO;
import test.spring.mapper.song.ImageBoard1Mapper;

@Service
public class ImageBoard1ServiceImpl implements ImageBoard1Service {

	@Autowired
	private ImageBoard1Mapper mapper;
	
	@Override
	public int boardListCount() {
		return mapper.boardListCount();
	}
	
	@Override
	public List boardList(String startRow, String endRow) {
		return mapper.boardList(startRow, endRow);
	}
	
	@Override
	public int myBoardListCount(String memId) {
		return mapper.myBoardListCount(memId);
	}
	
	@Override
	public List myBoardList(String memId, String startRow, String endRow) {
		return mapper.myBoardList(memId, startRow, endRow);
	}
	
	@Override
	public ImageBoard1DTO memContent(String memId) {
		return mapper.memContent(memId);
	}
	
	@Override
	public void write(ImageBoard1DTO dto) {
		mapper.write(dto);
	}
	
	@Override
	public void update(ImageBoard1DTO dto) {
		mapper.update(dto);
	}
	
	@Override
	public int delete(ImageBoard1DTO dto) {
		return mapper.delete(dto);
	}
	
	@Override
	public int subDeleteAll(ImageBoard1DTO dto) {
		return mapper.subDeleteAll(dto);
	}
	
	@Override
	public void subDelete(int num) {
		mapper.subDelete(num);
	}
	
	@Override
	public ImageBoard1DTO numContent(int num) {
		return mapper.numContent(num);
	}
	
	@Override
	public void addReadcount(int num, int count) {
		mapper.addReadcount(num, count);
	}
	
	@Override
	public int subBoardListCount(int num) {
		return mapper.subBoardListCount(num);
	}
	
	@Override
	public List subBoardList(int num, String startRow, String endRow) {
		return mapper.subBoardList(num, startRow, endRow);
	}
	
	@Override
	public int maxNum() {
		return mapper.maxNum();
	}
	
	@Override
	public void subWrite(ImageBoard1DTO dto) {
		mapper.subWrite(dto);
	}
	
	@Override
	public void reUpdate(int ref, int re_step) {
		mapper.reUpdate(ref, re_step);
	}
	
	@Override
	public ImageBoard1DTO boardcontent(int num) {
		return mapper.boardcontent(num);
	}
	
}
