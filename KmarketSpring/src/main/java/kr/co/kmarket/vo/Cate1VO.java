package kr.co.kmarket.vo;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cate1VO {
	@Id
	private int cate1;
	private String c1Name;
}
