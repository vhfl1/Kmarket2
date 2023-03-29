package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVO {
	
	private int revNo;
	private int prodNo;
	private String content;
	private String uid;
	private int rating;
	private String regip;
	private String rdate;
	private String prodName;
	
	public String getRdate() {
		return rdate.substring(2,10);
	}
}
