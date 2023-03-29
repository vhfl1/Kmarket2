package kr.co.kmarket.vo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponVO {

	private int no;
	private String couponName;
	private String discount;
	private String descript;
	private String couponDate;
	private String endDate;
	private int expire;
	
	public String getCouponDate() {
		return couponDate.substring(2,10);
	}
	public String getEndDate() {
		return endDate.substring(2,10);
	}
	public int getExpire() {
		//현재시간
		LocalDateTime now = LocalDate.now().atStartOfDay();
		//쿠폰만기일
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime end = LocalDateTime.parse(endDate, formatter);
		
		int between = (int)Duration.between(now, end).toDays();
		
		return between;
	}
	
}
