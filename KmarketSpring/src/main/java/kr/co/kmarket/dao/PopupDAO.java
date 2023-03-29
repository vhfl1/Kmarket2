package kr.co.kmarket.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderItemVO;
import kr.co.kmarket.vo.ReviewVO;

@Repository
@Mapper
public interface PopupDAO {
	
	//판매자정보
	public MemberVO selectSeller(String company);
	
	//수취확인
	public int updateOrdComplete(String ordNO);
	
	//상품리뷰
	public int insertReview(ReviewVO vo);
	
	//주문상세
	public OrderItemVO selectOrderInfo(String prodNO);
	
	//문의하기
	public int insertMyQna(CsVO vo);
}
