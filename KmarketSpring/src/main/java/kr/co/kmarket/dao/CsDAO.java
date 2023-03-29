package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CsVO;

@Mapper
@Repository
public interface CsDAO {

	//고객센터 메인
	public List<CsVO> selectNoticeMain();
	public List<CsVO> selectQnaMain();
	
	//공지사항
	public List<CsVO> selectNotice(int pg);
	public List<CsVO> selectNoticeByGroup(String arg0, int arg1);
	public CsVO selectNoticeArticle(int no);
	public int countNotice();
	public int countNoticeByGroup(String group);

	//자주묻는질문
	public List<CsVO> selectFaq(String group);
	public List<CsVO> selectFaqCate(String group);
	public CsVO selectFaqArticle(int no);
	
	//문의하기
	public List<CsVO> selectQna(int pg);
	public List<CsVO> selectQnaByGroup(String arg0, int arg1);
	public CsVO selectQnaArticle(int no);
	public CsVO selectQnaComment(int no);
	public int countQna();
	public int countQnaByGroup(String group);
	public void insertQna(CsVO vo);
	
}
