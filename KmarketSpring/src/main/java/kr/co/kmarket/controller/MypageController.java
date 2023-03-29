package kr.co.kmarket.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.MyPageService;
import kr.co.kmarket.vo.CouponVO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderItemVO;
import kr.co.kmarket.vo.PointVO;
import kr.co.kmarket.vo.ReviewVO;

/*
 * 담당 : 박진휘
 * 내용기록
 * 2023-02-14
 * 기본 컨트롤러 작업완료
 */
@MapperScan("kr.co.kmarket.dao")
@Controller
public class MypageController {
	
	@Autowired
	private MyPageService service;
	
	@GetMapping("mypage/home")
	public String home(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		List<OrderItemVO> order = service.selectLastOrder(uid);
		List<PointVO> point = service.selectLastPoint(uid);
		List<ReviewVO> review = service.selectLastReview(uid);
		List<CsVO> qna = service.selectLastQna(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		model.addAttribute("order", order);
		model.addAttribute("point", point);
		model.addAttribute("review", review);
		model.addAttribute("qna", qna);
		
		return "mypage/home";
	}
	
	@GetMapping("mypage/order")
	public String order(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		
		return "mypage/ordered";
	}
	
	@PostMapping("mypage/orderList")
	@ResponseBody
	public Map<String, Object> order(Principal principal, String start, String end, int pg) {
		String uid = principal.getName();
		List<OrderItemVO> list = service.selectMyOrder(uid, start, end, pg);
		
		//JSON 파싱
		Map<String, Object> map = new HashMap<>();
		map.put("result", list);
		
		return map;
	}
	
	@PostMapping("mypage/orderCount")
	@ResponseBody
	public Map<String, Object> orderCount(Principal principal, String start, String end) {
		String uid = principal.getName();
		int result = service.selectOrdCount(uid, start, end);
		
		//JSON 파싱
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	@GetMapping("mypage/point")
	public String point(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		
		return "mypage/point";
	}
	
	@PostMapping("mypage/pointList")
	@ResponseBody
	public Map<String, Object> point(Principal principal, String start, String end, int pg) {
		String uid = principal.getName();
		List<PointVO> list = service.selectMyPoint(uid, start, end, pg);
		
		//JSON 파싱
		Map<String, Object> map = new HashMap<>();
		map.put("result", list);
		
		return map;
	}
	
	@PostMapping("mypage/pointCount")
	@ResponseBody
	public Map<String, Object> pointCount(Principal principal, String start, String end) {
		String uid = principal.getName();
		int result = service.selectPointCount(uid, start, end);
		
		//JSON 파싱
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	@GetMapping("mypage/coupon")
	public String coupon(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		List<CouponVO> vo = service.selectMyCoupon(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		model.addAttribute("vo", vo);
		
		return "mypage/coupon";
	}
	
	@GetMapping("mypage/review")
	public String review(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		List<ReviewVO> vo = service.selectMyReview(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		model.addAttribute("vo", vo);
		
		return "mypage/review";
	}
	
	@GetMapping("mypage/qna")
	public String qna(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		List<CsVO> vo = service.selectMyQna(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		model.addAttribute("vo", vo);
		
		return "mypage/qna";
	}
	
	@GetMapping("mypage/info")
	public String info(Principal principal, Model model) {
		String uid = principal.getName();
		
		MemberVO info = service.selectUserinfo(uid);
		int count = service.selectMyQnaCount(uid);
		int ordCount = service.selectMyOrdCount(uid);
		
		model.addAttribute("info", info);
		model.addAttribute("count", count);
		model.addAttribute("ordCount", ordCount);
		
		return "mypage/info";
	}
	
	@PostMapping("mypage/info")
	public String info(MemberVO vo) {
		service.updateMember(vo);
		return "redirect:/mypage/info?success=101";
	}
	
}
