package kr.co.kmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.PopupDAO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderItemVO;
import kr.co.kmarket.vo.ReviewVO;

@Service
public class PopupService {

	@Autowired
	private PopupDAO dao;
	
	//판매자정보
	public MemberVO selectSeller(String company) {
		return dao.selectSeller(company); 
	}
	
	//수취확인
	public int updateOrdComplete(String ordNO) {
		return dao.updateOrdComplete(ordNO);
	}
	
	//상품리뷰
	public int insertReview(ReviewVO vo) {
		return dao.insertReview(vo);
	}
	
	//주문상세
	public OrderItemVO selectOrderInfo(String prodNO) {
		return dao.selectOrderInfo(prodNO);
	}
	
	//문의하기
	public int insertMyQna(CsVO vo) {
		return dao.insertMyQna(vo);
	}
	
}
