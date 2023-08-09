package test.spring.component.map;

import lombok.Data;

import java.util.List;

@Data
public class userDTO {
    private String K_NICK,PROFILE_IMG,K_EMAIL,K_GENDER,K_BIRTH,area;
    private int plan_num,day;
    private List<String> course;
}
