package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 담당 : 박진휘
 * 내용기록
 * 2023-02-14
 * 기본 컨트롤러 작업완료
 */
@Controller
public class TermsPolicyController {

	@GetMapping("termsPolicy/buyer")
	public String buyer() {
		return "termsPolicy/buyer";
	}
	
	@GetMapping("termsPolicy/seller")
	public String seller() {
		return "termsPolicy/seller";
	}
	
	@GetMapping("termsPolicy/finance")
	public String finance() {
		return "termsPolicy/finance";
	}
	
	@GetMapping("termsPolicy/location")
	public String location() {
		return "termsPolicy/location";
	}
	
	@GetMapping("termsPolicy/privacy")
	public String privacy() {
		return "termsPolicy/privacy";
	}
	
}
