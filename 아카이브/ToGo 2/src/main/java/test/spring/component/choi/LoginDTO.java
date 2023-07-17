package test.spring.component.choi;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginDTO {
	private int num;
	private String email;
	private String pw;
	private String nickname;
	private String mbti;
	@DateTimeFormat(pattern="yy/MM/dd HH:mm")
	private Date birth;
	
}
