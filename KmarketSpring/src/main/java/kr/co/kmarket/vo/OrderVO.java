package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	
	private int ordNo;
	private String ordUid;
	private int ordCount;
	private int ordPrice;
	private int ordDiscount;
	private int ordDelivery;
	private int savePoint;
	private int usedPoint;
	private int ordTotPrice;
	private String recipName;
	private String recipHP;
	private String recipZip;
	private String recipAddr1;
	private String recipAddr2;
	private int ordPayment;
	private int ordComplete;
	private String ordDate;
}
