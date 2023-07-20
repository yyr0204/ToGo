package test.spring.component.choi;
public class KakaoDTO {

		private long number;
		private String id;
		private String email;
		private String nickname;
		private String gender;
		private String birthday;
		private String mbti;
		public long getNumber() {
			return number;
		}
		public void setNumber(long number) {
			this.number = number;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getBirthday() {
			System.out.println("get");
			return birthday;
		}
		public void setBirthday(String birthday) {
			System.out.println("set");
			this.birthday = birthday;
		}
		public String getMbti() {
			return mbti;
		}
		public void setMbti(String mbti) {
			this.mbti = mbti;
		}
		
	
		
	}

