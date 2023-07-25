package test.spring.component.song;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ImageBoard1DTO {

	private String thumbnail;
	private int num;
	private String writer;
	private String subject;
	private String TripPlan;
	private String content;
	private String image;
	private String passwd;
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private Date reg_date;
	private int readcount;
	private String ip;
	private int ref;
	private int re_step;
	private int re_level;
	private int boardnum;
}
