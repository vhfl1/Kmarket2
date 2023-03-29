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
public class CompanyController {

	@GetMapping("company/index")
	public String home() {
		return "company/index";
	}
	
	@GetMapping("company/introduce")
	public String introduce() {
		return "company/introduce";
	}
	
	@GetMapping("company/manage")
	public String manage() {
		return "company/manage";
	}
	
	@GetMapping("company/notice")
	public String notice() {
		return "company/notice";
	}
	
	@GetMapping("company/promote")
	public String promote() {
		return "company/promote";
	}
	
}
