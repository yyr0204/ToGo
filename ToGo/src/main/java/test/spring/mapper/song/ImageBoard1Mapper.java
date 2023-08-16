package test.spring.mapper.song;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import test.spring.component.song.ImageBoard1DTO;

public interface ImageBoard1Mapper {

	public int boardListCount();
	
	public List boardList(@Param("startRow") String startRow, @Param("endRow") String endRow);
	
	public int myBoardListCount(String memId);
	
	public List myBoardList(@Param("memId") String memId, @Param("startRow") String startRow, @Param("endRow") String endRow);
	
	public ImageBoard1DTO memContent(String memId);
	
	public void write(ImageBoard1DTO dto);
	
	public void update(ImageBoard1DTO dto);
	
	public int delete(ImageBoard1DTO dto);
	
	public int subDeleteAll(ImageBoard1DTO dto);
	
	public void subDelete(int num);
	
	public ImageBoard1DTO numContent(int num);
	
	public void addReadcount(@Param("num") int num, @Param("readcount") int count);
	
	public int subBoardListCount(int num);
	
	public List subBoardList(@Param("num") int num, @Param("startRow") String startRow, @Param("endRow") String endRow);
	
	public int maxNum();
	
	public void subWrite (ImageBoard1DTO dto);
	
	public void reUpdate(@Param("ref") int ref, @Param("re_step") int re_step);
	
	public ImageBoard1DTO boardcontent(int num);
	
}
