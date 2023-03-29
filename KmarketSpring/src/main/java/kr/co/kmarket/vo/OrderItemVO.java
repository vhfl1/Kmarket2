package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemVO {

	private int ordNO;
	private int prodNO;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int total;
	private String uid;
	private String rdate;
	
	//추가
	private String prodName;
	private String company;
	private String thumb1;
	private String ordComplete;
	public String getRdate() {
		return rdate.substring(2,10);
	}
	
	private String recipname;
	private String recipHp;
	private String recipZip;
	private String recipAddr1;
	private String recipAddr2;
}
