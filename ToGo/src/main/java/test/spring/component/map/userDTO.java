package test.spring.component.map;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class userDTO {
    private String K_NICK,PROFILE_IMG,K_EMAIL,K_GENDER,K_BIRTH,area;
    private int plan_num,day;
    private List<String> course;
    private String id,title;
    private Date startDay;
    private Date endDay;
    private String course1;
    private String course2;
    private String course3;
    private String course4;
    private String course5;
    private String course6;

}
