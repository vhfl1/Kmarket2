package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.ProductVO;

/*
 * 날짜 : 2023/02/10 
 * 이름 : 이민혁
 * 내용 : Admin DAO 기능구현 
 * 
 */

@Mapper
@Repository
public interface AdminDAO {
	
	public void insertAdmin();
	
	//ADMIN PRODUCT
	//관리자 상품등록 
	public void insertAdminProduct(ProductVO pv);
	
	//관리자 상품등록 Cate1 조회
	public List<Cate1VO> selectProductCate1s();
	
	//관리자 상품등록 Cate1 로 Cate2를 조회
	public List<Cate2VO> selectProductCate2s(int cate1);
	
	//상품 목록 페이징 작업
	public int selectCountTotal();
	
	//관리자 상품 목록
	public List<ProductVO> selectAdminProductList(int start);
	
	//관리자 상품 삭제
	public int productDelete(@RequestParam("prodNo") int no);
	
	
	//ADMIN INDEX
	//관리자 index 공지사항 목록
	public List<CsVO> selectIndexCsNoticeList();
	
	//관리자 index 문의하기 목록
	public List<CsVO> selectCsQnaList();
	
	//ADMIM NOTICE
	//관리자 고객관리 공지사항 목록
	public List<CsVO> selectCsNoticeList(int start);
	
	//관리자 공지사항 페이징 작업
	public int selectCountNoticeTotal();
	
	//관리자 공지사항 보기
	public CsVO selectNoticeArticle(int no);
	
	//관리자 공지사항 수정
	public void updateNoticeArticle(CsVO vo);
	
	//관리자 공지사항 삭제
	public void deleteNoticeArticle(int no);
	
	
	//ADMIN QNA
	//관리자 문의하기 목록
	public List<CsVO> selectQnaArticles(int start);
	
	//관리자 문의하기 보기
	public CsVO selectQnaArticle(int no);
	
	//관리자 문의하기 답변하기
	public void insertQnaReply(CsVO vo);
	
	//관리자 문의 status
	public void updateQnaStatus(CsVO vo);
	
	//관리자 문의하기 답변보기
	public CsVO selectQnaReply(int no);
	
	//관리자 문의하기 카테고리 검색
	public List<CsVO> selectQnaSearch(@Param("group") String group, @Param("cate") String cate, @Param("start") int start);
	
	//관리자 문의하기 게시글 삭제
	public void deleteQnaArticle(@RequestParam("no") int no);
	
	//관리자 문의하기 페이징 작업
	public int selectCountQna();
	
	public int selectCountQna2(@Param("group") String group, @Param("cate") String cate);
	
	
}