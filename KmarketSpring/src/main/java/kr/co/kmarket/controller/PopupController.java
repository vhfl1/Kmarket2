package kr.co.kmarket.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.PopupService;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderItemVO;
import kr.co.kmarket.vo.ReviewVO;

@Controller
@MapperScan("kr.co.kmarket.dao")
public class PopupController {
	
	@Autowired
	private PopupService service;
	
	//판매자정보
	@GetMapping("my/sellerInfo/{company}")
	public String seller(@PathVariable("company") String company, Model model) {
		
		MemberVO vo = service.selectSeller(company);
		
		model.addAttribute("vo", vo);
		
		return "popup/sellerInfo";
	}
	
	//수취확인
	@GetMapping("my/confirm/{ordNO}")
	public String confirm(@PathVariable("ordNO") String ordNO, Model model) {
		
		model.addAttribute("ordNO", ordNO);
		
		return "popup/confirmPopup";
	}
	
	@PostMapping("my/confirm/{ordNO}")
	@ResponseBody
	public Map<String, Integer> confirm(@PathVariable("ordNO") String ordNO) {
		
		int result = service.updateOrdComplete(ordNO);
		
		Map<String, Integer> res = new HashMap<>();
		res.put("result", result);
		
		return res;
	}
	
	//상품리뷰
	@GetMapping("my/review")
	public String review(Principal principal, String prodName, String prodNo, Model model) {
		String uid = principal.getName();
		
		model.addAttribute("uid", uid);
		model.addAttribute("prodName", prodName);
		model.addAttribute("prodNo", prodNo);
		
		return "popup/reviewPopup";
	}
	
	@PostMapping("my/review")
	@ResponseBody
	public Map<String, Integer> review(ReviewVO vo, HttpServletRequest req) {
		
		vo.setRegip(req.getRemoteAddr());
		
		int result = service.insertReview(vo);
		
		Map<String, Integer> res = new HashMap<>();
		res.put("result", result);
		
		return res;
	}
	
	//주문상세
	@GetMapping("my/orderInfo/{prodNO}")
	public String orderInfo(@PathVariable("prodNO") String prodNO, Model model) {
		
		OrderItemVO vo = service.selectOrderInfo(prodNO);
		
		model.addAttribute("vo", vo);
		
		return "popup/orderInfo";
	}
	
	@GetMapping("my/qna/{company}")
	public String qna(@PathVariable("company") String company, Principal principal, Model model) {
		
		String uid = principal.getName();
		
		model.addAttribute("uid",uid);
		model.addAttribute("company",company);
		
		return "popup/qnaPopup";
	}

	@PostMapping("my/qna")
	@ResponseBody
	public Map<String, Integer> qna(CsVO vo, HttpServletRequest req) {
		
		vo.setGroup("판매자 게시판");
		vo.setRegip(req.getRemoteAddr());
		
		int result = service.insertMyQna(vo);
		
		Map<String, Integer> res = new HashMap<>();
		res.put("result", result);
		
		return res;
	}
	
}
