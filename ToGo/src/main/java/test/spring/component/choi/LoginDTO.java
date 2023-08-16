package test.spring.component.choi;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginDTO {
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	private int num;
	private String email;
	private String pw;
	private String nickname;
	private String mbti;
	@DateTimeFormat(pattern="yy/MM/dd HH:mm")
	private Date birth;
	
}
