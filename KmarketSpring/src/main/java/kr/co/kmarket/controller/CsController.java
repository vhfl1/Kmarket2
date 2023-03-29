package kr.co.kmarket.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;

/*
 * 담당 : 박진휘
 * 내용기록
 * 2023-02-09
 * 고객센터 기능구현 작업완료
*/
@Controller
@MapperScan("kr.co.kmarket.dao")
public class CsController {

	@Autowired
	private CsService service;
	
	//고객센터 메인
	@GetMapping("cs")
	public String index(Model model) {
		List<CsVO> notice = service.selectNoticeMain();
		List<CsVO> qna = service.selectQnaMain();
		model.addAttribute("vo1", notice);
		model.addAttribute("vo2", qna);
		return "cs/index";
	}
	//공지사항 리스트
	@GetMapping("cs/notice/list")
	public String noticeList(Model model, String group, int pg) {
		List<CsVO> articles = service.selectNotice(group, pg);
		//페이징
		int lastPageNum = service.countNotice(group);
		int[] page= service.currentPage(pg, lastPageNum);
		
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		model.addAttribute("group", group);
		model.addAttribute("pg", pg);
		return "cs/notice_list";
	}
	//공지사항 상세보기
	@GetMapping("cs/notice/view")
	public String noticeView(Model model, String group, int no, int pg) {
		CsVO vo = service.selectNoticeArticle(no);
		model.addAttribute("vo", vo);
		model.addAttribute("group", group);
		model.addAttribute("pg", pg);
		return "cs/notice_view";
	}
	//자주묻는질문 리스트
	@GetMapping("cs/faq/list")
	public String faqList(Model model, String group) {
		System.out.println(group);
		List<CsVO> articles = service.selectFaq(group);
		List<CsVO> cate = service.selectFaqCate(group);
		model.addAttribute("articles", articles);
		model.addAttribute("group", group);
		model.addAttribute("cate", cate);
		return "cs/faq_list";
	}
	//자주묻는질문 상세보기
	@GetMapping("cs/faq/view")
	public String faqView(Model model, String group, int no) {
		CsVO vo = service.selectFaqArticle(no);
		model.addAttribute("vo", vo);
		model.addAttribute("group", group);
		return "cs/faq_view";
	}
	//문의하기 리스트
	@GetMapping("cs/qna/list")
	public String qnaList(Model model, String group, int pg) {
		List<CsVO> articles = service.selectQna(group, pg);
		//페이징
		int lastPageNum = service.countQna(group);
		int[] page= service.currentPage(pg, lastPageNum);
		
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		model.addAttribute("group", group);
		model.addAttribute("pg", pg);
		return "cs/qna_list";
	}
	//문의하기 상세보기
	@GetMapping("cs/qna/view")
	public String qnaView(Model model, String group, int no, int pg) {
		CsVO vo = service.selectQnaArticle(no);
		model.addAttribute("vo", vo);
		model.addAttribute("group", group);
		model.addAttribute("pg", pg);
		return "cs/qna_view";
	}
	//문의하기 글쓰기
	@GetMapping("cs/qna/write")
	public String qnaWrite(Model model, String group, int pg) {
		model.addAttribute("group", group);
		model.addAttribute("pg", pg);
		return "cs/qna_write";
	}
	@PostMapping("cs/qna/write")
	public String qnaWrite(Model model, CsVO vo, HttpServletRequest req) {
		vo.setRegip(req.getRemoteAddr());
		service.insertQna(vo);
		
		String encodeResult = "";
		try {
			encodeResult = URLEncoder.encode(vo.getGroup(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redirect:/cs/qna/list?group="+encodeResult+"&pg=1";
	}
	
}
