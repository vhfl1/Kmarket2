package kr.co.kmarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name= "km_member")
public class MemberEntity {
	@Id
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
}
