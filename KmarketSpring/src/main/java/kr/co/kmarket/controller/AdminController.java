package kr.co.kmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.SearchResultVO;
import lombok.extern.slf4j.Slf4j;

/*
 * 담당 : 이민혁
 * 내용기록
 * 2023-02-10
 * 컨트롤러, 서비스, 통합구현 기본
 * 
 */

@Slf4j
@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	//index
	@GetMapping("admin")
	public String index(Model model) {
		
		List<CsVO> noticeList = service.selectIndexCsNoticeList();
		List<CsVO> qnaList = service.selectCsQnaList();
		
		model.addAttribute("notice", noticeList);
		model.addAttribute("qna", qnaList);
		
		return "admin/index";
	}
	
	//관리자 상품 목록 - 2023/02/15
	@GetMapping("admin/product/list")
	public String list(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<ProductVO> article = service.selectAdminProductList(start);
		
		model.addAttribute("article", article);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		/*System.out.println("currentPage : " + currentPage);
		System.out.println("lastPageNum : " + lastPageNum);
		System.out.println("pageStartNum : " + pageStartNum);
		System.out.println("groups[0] : " + groups[0]);*/
		
		return "admin/product/list";
	}
	
	//관리자 상품 삭제 - 2023/02/16
	@ResponseBody
	@GetMapping("admin/productDelete")
	public Map<String, Integer> productDelete (@RequestParam("prodNo") List<Integer> prodNo) {
		
		for(int no : prodNo) {
			service.productDelete(no);
		}
		
		Map<String, Integer> resultMap = new HashMap<>();
		
		resultMap.put("result", prodNo.size());
		
		/*System.out.println("result : " + result);
		System.out.println("resultMap : " + resultMap);*/
		
		return resultMap;
	}
	
	//관리자 상품 등록 1차 카테고리 - 2023/02/15
	@GetMapping("admin/product/register")
	public String register(Model model) {
		
		List<Cate1VO> cate1 = service.selectProductCate1s();
		
		model.addAttribute("cate1",cate1);
		
		return "admin/product/register";
	}
	
	//관리자 상품 등록 2차 카테고리 - 2023/02/15
	@ResponseBody 
	@GetMapping("admin/selectCate2")
	public Map<String, List<Cate2VO>> selectCate2(int cate1) {
		
		List<Cate2VO> cate2s = service.selectProductCate2s(cate1);
		
		Map<String, List<Cate2VO>> map = new HashMap<>();
		
		map.put("result", cate2s);
		
		System.out.println("cate2 : " + cate2s);
		
		return map;
	}
	
	//상품등록 - 2023/02/13
	@PostMapping("admin/product/register")
	public String register(ProductVO pv, HttpServletRequest req) {
		
		String ip = req.getRemoteAddr();
		pv.setIp(ip);
		
		service.insertAdminProduct(pv);
		
		return "redirect:/admin/product/list";
	}

	//관리자 CS - Notice List
	@GetMapping("admin/cs/notice/list")
	public String notice(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountNoticeTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<CsVO> notice = service.selectCsNoticeList(start);
		
		model.addAttribute("notice", notice);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		/*System.out.println("currentPage : " + currentPage);
		System.out.println("lastPageNum : " + lastPageNum);
		System.out.println("pageStartNum : " + pageStartNum);
		System.out.println("groups[0] : " + groups[0]);
		System.out.println("groups[1] : " + groups[1]);*/
		
		return "admin/cs/notice_list";
	}

	//관리자 CS - Notice View
	@GetMapping("admin/cs/notice/view")
	public String view(int no, Model model) {
		
		CsVO article = service.selectNoticeArticle(no);
		
		model.addAttribute("article", article);
		
		return "admin/cs/notice_view";
	}
	
	//관리자 CS - Notice modify
	@GetMapping("admin/cs/notice/modify")
	public String modify(int no, Model model) {
		
		CsVO article = service.selectNoticeArticle(no);
		
		model.addAttribute("article", article);
		
		return "admin/cs/notice_modify";
	}
	
	//관리자 CS - Notice modify
	@PostMapping("admin/cs/notice/modify")
	public String modify(CsVO vo) {
		service.updateNoticeArticle(vo);
		return "redirect:/admin/cs/notice/list";
	}
	
	//관리자 CS - Notice delete
	@GetMapping("admin/cs/notice/delete")
	public String deleteNotice(int no) {
		service.deleteNoticeArticle(no);
		return "redirect:/admin/cs/notice/list";
	}
	
	//관리자 CS - Qna List
	@GetMapping("admin/cs/qna/list")
	public String qna(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountQna();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<CsVO> articles = service.selectQnaArticles(start);
		
		model.addAttribute("articles", articles);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		System.out.println("total : " + total);
		System.out.println("lastPageNum : " + lastPageNum);
		System.out.println("pageStartNum : " + pageStartNum);
		
		return "admin/cs/qna_list";
	}
	
	//관리자 CS - Qna View
	@GetMapping("admin/cs/qna/view")
	public String qna(int no, Model model) {
		
		CsVO article = service.selectQnaReply(no);
		
		model.addAttribute("article", article);
		
		return "admin/cs/qna_view";
	}
	
	//관리자 CS - Qna Reply
	@GetMapping("admin/cs/qna/reply")
	public String reply(Model model, int no) {
		
		CsVO article = service.selectQnaArticle(no);
		
		model.addAttribute("article", article);
		
		return "admin/cs/qna_reply";
	}
	
	////관리자 CS - Qna Reply
	@PostMapping("admin/cs/qna/reply")
	public String reply(int no, CsVO vo,HttpServletRequest req) {
		
		String ip = req.getRemoteAddr();
		vo.setRegip(ip);
		
		service.insertQnaReply(vo);
		service.updateQnaStatus(vo);
		
		return "redirect:/admin/cs/qna/list";
	}
	
	//관리자 CS - Qna Search
	@ResponseBody
	@GetMapping("admin/qna_search")
	public SearchResultVO Search(@RequestParam("group") String group, String cate, String pg) {

		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		List<CsVO> search = service.selectQnaSearch(group, cate, start);
		
		int total = service.selectCountQna2(group, cate);
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		SearchResultVO vo = new SearchResultVO();
		vo.setCurrentPage(currentPage);
		vo.setSearch(search);
		vo.setLastPageNum(lastPageNum);
		vo.setPageStartNum(pageStartNum);
		vo.setGroups(groups);
		vo.setGroup(group);
		vo.setCate(cate);
		
		/*System.out.println("total : " + total);
		System.out.println("lastPageNum : " + lastPageNum);
		System.out.println("pageStartNum : " + pageStartNum);*/
		
		
		return vo;
	}
	
	//관리자 CS - Qna Delete
	@ResponseBody
	@GetMapping("admin/deleteQnaArticle")
	public Map<String, Integer> deleteQna(@RequestParam("arr") List<Integer> arr) {
		
		for(int no : arr) {
			service.deleteQnaArticle(no);
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("result", arr.size());
		
		System.out.println(map);
		System.out.println(arr.size());
		
		return map;
	}
}