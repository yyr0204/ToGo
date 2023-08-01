package test.spring.component.song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PlanDTO {

	public String area;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date startDay;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date endDay;
	
	public List mainList;
	
	int totalDay;
}
