package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.MyPageDAO;
import kr.co.kmarket.vo.CouponVO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderItemVO;
import kr.co.kmarket.vo.PointVO;
import kr.co.kmarket.vo.ReviewVO;

@Service
public class MyPageService {
	
	@Autowired
	private MyPageDAO dao;
	
	public MemberVO selectUserinfo(String uid) {
		return dao.selectUserinfo(uid);
	}
	public int selectMyQnaCount(String uid) {
		return dao.selectMyQnaCount(uid);
	}
	public int selectMyOrdCount(String uid) {
		return dao.selectMyOrdCount(uid);
	}
	public List<OrderItemVO> selectLastOrder(String uid) {
		return dao.selectLastOrder(uid);
	}
	public List<PointVO> selectLastPoint(String uid) {
		return dao.selectLastPoint(uid);
	}
	public List<ReviewVO> selectLastReview(String uid) {
		return dao.selectLastReview(uid);
	}
	public List<CsVO> selectLastQna(String uid) {
		return dao.selectLastQna(uid);
	}
	
	//전체주문내역
	public List<OrderItemVO> selectMyOrder(String param1, String param2, String param3 , int param4){
		return dao.selectMyOrder(param1, param2, param3, param4);
	}
	public int selectOrdCount(String param1, String param2, String param3) {
		int result = dao.selectOrdCount(param1, param2, param3);
		
		if(result % 10 == 0) {
			result = result / 10;
		}else {
			result = result / 10 + 1;
		}
		
		return result;
	}
	//포인트내역
	public List<PointVO> selectMyPoint(String param1, String param2, String param3, int param4){
		return dao.selectMyPoint(param1, param2, param3, param4);
	}
	public int selectPointCount(String param1, String param2, String param3) {
		int result = dao.selectOrdCount(param1, param2, param3);
		
		if(result % 10 == 0) {
			result = result / 10;
		}else {
			result = result / 10 + 1;
		}
		
		return result;
	}
	//쿠폰
	public List<CouponVO> selectMyCoupon(String uid){
		return dao.selectMyCoupon(uid);
	}
	//나의리뷰
	public List<ReviewVO> selectMyReview(String uid){
		return dao.selectMyReview(uid);
	}
	//문의하기
	public List<CsVO> selectMyQna(String uid){
		return dao.selectMyQna(uid);
	}
	//회원수정
	public void updateMember(MemberVO vo) {
		dao.updateMember(vo);
	}
}
