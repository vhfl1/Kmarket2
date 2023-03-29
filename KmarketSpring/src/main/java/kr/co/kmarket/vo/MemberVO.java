package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	
	private String uid;
	private String pass1;
	private String pass2;
	private String pass;
	private String name;
	private int gender;
	private String hp;
	private String email;
	private int type;
	private int point;
	private int level;
	private String zip;
	private String addr1;
	private String addr2;
	private String company;
	private String ceo;
	private String bizRegNum;
	private String comRegNum;
	private String tel;
	private String manager;
	private String managerHp;
	private String fax;
	private String regip;
	private String wdate;
	private String rdate;
	private String sessId;
	private String sessLimitDate;
	
	//마이페이지
	private int order;
	private int coupon;
	private int qna;
	
	private String maskName;
	public String getMaskName() {
		int len = name.length();
		String newName = name;
		if(len == 2) {
			char f = newName.charAt(0);
			newName = f + "*";
		}else if(len == 3) {
			char f = newName.charAt(0);
			char l = newName.charAt(2);
			newName = f + "*" + l;
		}else {
			char f = newName.charAt(0);
			char l = newName.charAt(3);
			newName = f + "**" + l;
		}
		return newName;
	}
	
	private String email1;
	private String email2;
	public String getEmail1() {
		int index = email.indexOf("@");
		return email.substring(0, index);
	}
	public String getEmail2() {
		int index = email.indexOf("@");
		return email.substring(index+1);
	}
	
	private String hp1;
	private String hp2;
	private String hp3;
	public String getHp1() {
		int index = hp.indexOf("-");
		return hp.substring(0, index);
	}
	public String getHp2() {
		int index1 = hp.indexOf("-");
		int index2 = hp.lastIndexOf("-");
		return hp.substring(index1+1, index2);
	}
	public String getHp3() {
		int index = hp.lastIndexOf("-");
		return hp.substring(index+1);
	}
}
