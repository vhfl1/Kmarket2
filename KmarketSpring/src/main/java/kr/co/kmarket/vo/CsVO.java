package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsVO {

	private int no;
	private String group;
	private int parent;
	private int comment;
	private String cate;
	private String title;
	private int hit;
	private String content;
	private String uid;
	private String regip;
	private String rdate;
	
	//추가필드
	private int count;
	private String uidMask;
	private String reply;
	private int status;
	private int start;
	
	private String company;
	
	public String getRdate() {
		return rdate.substring(2, 10);
	}
	
	public String getUidMask() {
		int len = uid.length();
		len = len - 2;
		return uid.substring(0, len) + "**";
	}

	
}
