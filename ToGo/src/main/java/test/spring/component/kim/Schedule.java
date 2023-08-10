package test.spring.component.kim;

import java.util.Date;

import lombok.Data;

@Data
public class Schedule {
	
	int plan_num;
	String id;
	String title;
	Date startday;
	Date endday;

}
