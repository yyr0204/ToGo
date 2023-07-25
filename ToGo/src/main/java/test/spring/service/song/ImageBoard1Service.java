package test.spring.service.song;

import java.util.List;

import test.spring.component.song.ImageBoard1DTO;

public interface ImageBoard1Service {

	public int boardListCount();
	
	public List boardList(String startRow, String endRow);
	
	public int myBoardListCount(String memId);
	
	public List myBoardList(String memId, String startRow, String endRow);
	
	public ImageBoard1DTO memContent(String memId);
	
	public void write(ImageBoard1DTO dto);
	
	public void update(ImageBoard1DTO dto);
	
	public int delete(ImageBoard1DTO dto);
	
	public int subDeleteAll(ImageBoard1DTO dto);
	
	public void subDelete(int num);
	
	public ImageBoard1DTO numContent(int num);
	
	public void addReadcount(int num, int count);
	
	public int subBoardListCount(int num);
	
	public List subBoardList(int num, String startRow, String endRow);
	
	public int maxNum();
	
	public void subWrite(ImageBoard1DTO dto);
	
	public void reUpdate(int ref, int re_step);
	
	public ImageBoard1DTO boardcontent(int num);
	
}
