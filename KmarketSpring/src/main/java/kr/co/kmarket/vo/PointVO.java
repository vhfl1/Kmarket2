package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointVO {

	private int pointNo;
	private String uid;
	private int ordNo;
	private int point;
	private String pointDate;
	private String pointDateEnd;
	
	public String getPointDate() {
		return pointDate.substring(2,10);
	}
	public String getPointDateEnd() {
		return pointDateEnd.substring(2,10);
	}
	
}
