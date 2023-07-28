package test.spring.component.park;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeachDTO {
	@JsonProperty("개장일")
	private String openDate;
	@JsonProperty("시도1")
	private String sido1;
	@JsonProperty("시도2")
	private String sido2;
	@JsonProperty("주소")
	private String address;
	@JsonProperty("폐장일")
	private String closingDate;
	@JsonProperty("해수욕장명")
	private String beachName;
	
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getSido1() {
		return sido1;
	}
	public void setSido1(String sido1) {
		this.sido1 = sido1;
	}
	public String getSido2() {
		return sido2;
	}
	public void setSido2(String sido2) {
		this.sido2 = sido2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public String getBeachName() {
		return beachName;
	}
	public void setBeachName(String beachName) {
		this.beachName = beachName;
	}
    
}

