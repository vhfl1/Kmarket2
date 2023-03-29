package kr.co.kmarket.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchResultVO {
	
	private int start;
	private int currentPage;
	private List<CsVO> search;	
	private int lastPageNum;
	private int pageStartNum;
	private int[] groups;

	private String group;
	private String cate;
}
