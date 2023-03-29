package kr.co.kmarket.controller;

import java.security.Principal;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVO;

@Controller
@MapperScan("kr.co.kmarket.dao")
public class MainController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = {"", "index"})
	public String index(Model model, Principal principal) {
		List<ProductVO> hitProduct = service.selectProductByParam("hit");
		List<ProductVO> recommendProduct = service.selectProductByParam("score");
		List<ProductVO> discountProduct = service.selectProductByParam("discount");
		List<ProductVO> bestProduct = service.selectProductByParam("sold");
		List<ProductVO> newProduct = service.selectProductByParam("prodNo");
		
		model.addAttribute("hitProduct", hitProduct);
		model.addAttribute("recommendProduct", recommendProduct);
		model.addAttribute("discountProduct", discountProduct);
		model.addAttribute("bestProduct", bestProduct);
		model.addAttribute("newProduct", newProduct);
		
		return "index";
	}
	
}
