package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.vo.CsVO;

@Service
public class CsService {

	@Autowired
	private CsDAO dao;
	
	//고객센터 메인
	public List<CsVO> selectNoticeMain(){
		return dao.selectNoticeMain();
	}
	public List<CsVO> selectQnaMain(){
		return dao.selectQnaMain();
	}
	
	//공지사항
	public List<CsVO> selectNotice(String group, int pg){
		pg = (pg-1) * 10;
		
		if(group.equals("all")) {
			return dao.selectNotice(pg);
		}else {
			return dao.selectNoticeByGroup(group, pg);
		}
	}
	public CsVO selectNoticeArticle(int no) {
		return dao.selectNoticeArticle(no);
	}
	public int countNotice(String group) {
		int result = 0;
		if(group.equals("all")) {
			result = dao.countNotice();
		}else {
			result = dao.countNoticeByGroup(group);
		}
		
		//총 페이지 개수
		if(result % 10 == 0) {
			result = result / 10;
		}else {
			result = result / 10 + 1;
		}
		
		return result;
	}
	
	//자주묻는질문
	public List<CsVO> selectFaq(String group){
		return dao.selectFaq(group);
	}
	public List<CsVO> selectFaqCate(String group){
		return dao.selectFaqCate(group);
	}
	public CsVO selectFaqArticle(int no) {
		return dao.selectFaqArticle(no);
	}
	
	//문의하기
	public List<CsVO> selectQna(String group, int pg){
		pg = (pg-1) * 10;
		
		if(group.equals("all")) {
			return dao.selectQna(pg);
		}else {
			return dao.selectQnaByGroup(group, pg);
		}
	}
	public CsVO selectQnaArticle(int no) {
		return dao.selectQnaArticle(no);
	}
	public CsVO selectQnaComment(int no) {
		return dao.selectQnaComment(no);
	}
	public void insertQna(CsVO vo) {
		dao.insertQna(vo);
	}
	public int countQna(String group) {
		int result = 0;
		if(group.equals("all")) {
			result = dao.countQna();
		}else {
			result = dao.countQnaByGroup(group);
		}
		
		//총 페이지 개수
		if(result % 10 == 0) {
			result = result / 10;
		}else {
			result = result / 10 + 1;
		}
		
		return result;
	}
	
	//페이지
	public int[] currentPage(int pg, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(pg / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int num = (pg - 1) * 10;
		
		int[] groups = {pageGroupStart, pageGroupEnd, lastPageNum, num};
		return groups;
	}
	
}
