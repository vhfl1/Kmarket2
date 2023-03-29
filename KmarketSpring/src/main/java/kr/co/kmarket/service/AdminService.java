package kr.co.kmarket.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

/*
 * 날짜 : 2023/02/10
 * 이름 : 이민혁
 * 내용 : Admin Service 기능구현
 *
 */

@Slf4j
@Service
public class AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	public void insertAdmin() {}
	
	public void insertAdminProduct(ProductVO pv) {
		
		fileUpload(pv);
		
		dao.insertAdminProduct(pv);
	}
	
	public List<Cate1VO> selectProductCate1s() {
		return dao.selectProductCate1s();
	}
	
	public List<Cate2VO> selectProductCate2s(int cate1) {
		return dao.selectProductCate2s(cate1);
	}
	
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	
	public int selectCountNoticeTotal() {
		return dao.selectCountNoticeTotal();
	}
	
	public int selectCountQna() {
		return dao.selectCountQna();
	}
	
	public int selectCountQna2(String group, String cate) {
		return dao.selectCountQna2(group, cate);
	}
	
	public List<ProductVO> selectAdminProductList(int start) {
		return dao.selectAdminProductList(start);
	}
	
	//관리자 상품 삭제
	public int productDelete(int no) {
		return dao.productDelete(no);
	};
	
	//관리자 Index 공지사항 list
	public List<CsVO> selectIndexCsNoticeList() {
		return dao.selectIndexCsNoticeList();
	};
	
	//관리자 Index 문의하기 list
	public List<CsVO> selectCsQnaList(){
		return dao.selectCsQnaList();
	};
	
	//관리자 공지사항 게시글 list
	public List<CsVO> selectCsNoticeList(int start){
		return dao.selectCsNoticeList(start);
	}
	
	//관리자 공지사항 게시글 View
	public CsVO selectNoticeArticle(int no) {
		return dao.selectNoticeArticle(no);
	}
	
	//관리자 공지사항 게시글 Modify
	public void updateNoticeArticle(CsVO vo) {
		dao.updateNoticeArticle(vo);
	}
	
	//관리자 공지사항 게시글 Delete
	public void deleteNoticeArticle(int no) {
		dao.deleteNoticeArticle(no);
	}
	
	//관리자 문의하기 게시글 list 2023/02/20
	public List<CsVO> selectQnaArticles(int start){
		return dao.selectQnaArticles(start);
	}
	
	//관리자 문의하기 게시글 View 2023/02/20
	public CsVO selectQnaArticle(int no) {
		return dao.selectQnaArticle(no);
	};
	
	//관리자 문의하기 게시글 Reply 2023/02/20
	public void insertQnaReply(CsVO vo) {
		dao.insertQnaReply(vo);
	};
	
	//관리자 문의하기 status 2023/02/20
	public void updateQnaStatus(CsVO vo) {
		dao.updateQnaStatus(vo);
	};
	
	//관리자 문의하기 목록 답변보기 2023/02/20
	public CsVO selectQnaReply(int no) {
		return dao.selectQnaReply(no);
	};
	
	//관리자 문의하기 카테고리 검색 2023/02/21
	public List<CsVO> selectQnaSearch (String group, String cate, int start){
		return dao.selectQnaSearch(group, cate, start);
	};
	
	//관리자 문의하기 게시글 삭제 2023/02/21
	public void deleteQnaArticle(int no) {
		dao.deleteQnaArticle(no);
	}
	
	
	

	
	//파일 업로드
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	public void fileUpload(ProductVO vo) {
		
		MultipartFile file1 = vo.getFile1();
		MultipartFile file2 = vo.getFile2();
		MultipartFile file3 = vo.getFile3();
		MultipartFile file4 = vo.getFile4();
		
		if(!file1.isEmpty()) {
			//시스템 경로
			String path = new File(uploadPath).getAbsolutePath();
			
			// 새 파일명 생성
			String oriName = file1.getOriginalFilename();
			String ext = oriName.substring(oriName.lastIndexOf("."));
			String newName = UUID.randomUUID().toString()+ext;
			
			vo.setThumb1(newName);
			
			//파일 저장
			try {
				file1.transferTo(new File(path, newName));
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
			}catch(IOException e) {
				log.error(e.getMessage());
			}
		}
		
		if(!file2.isEmpty()) {
			//시스템 경로
			String path = new File(uploadPath).getAbsolutePath();
			
			// 새 파일명 생성
			String oriName = file2.getOriginalFilename();
			String ext = oriName.substring(oriName.lastIndexOf("."));
			String newName = UUID.randomUUID().toString()+ext;
			
			vo.setThumb2(newName);
			
			//파일 저장
			try {
				file2.transferTo(new File(path, newName));
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
			}catch(IOException e) {
				log.error(e.getMessage());
			}
		}
		
		if(!file3.isEmpty()) {
			//시스템 경로
			String path = new File(uploadPath).getAbsolutePath();
			
			// 새 파일명 생성
			String oriName = file3.getOriginalFilename();
			String ext = oriName.substring(oriName.lastIndexOf("."));
			String newName = UUID.randomUUID().toString()+ext;
			
			vo.setThumb3(newName);
			
			//파일 저장
			try {
				file3.transferTo(new File(path, newName));
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
			}catch(IOException e) {
				log.error(e.getMessage());
			}
		}
		
		if(!file4.isEmpty()) {
			//시스템 경로
			String path = new File(uploadPath).getAbsolutePath();
			
			// 새 파일명 생성
			String oriName = file4.getOriginalFilename();
			String ext = oriName.substring(oriName.lastIndexOf("."));
			String newName = UUID.randomUUID().toString()+ext;
			
			vo.setDetail(newName);
			
			//파일 저장
			try {
				file4.transferTo(new File(path, newName));
			}catch (IllegalStateException e) {
				log.error(e.getMessage());
			}catch(IOException e) {
				log.error(e.getMessage());
			}
		}
	}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	// 페이지 시작값
	public int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	// 페이지 시작 번호
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	
	// 페이지 그룹
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		
		int[] groups = {groupStart, groupEnd};
		
		/*System.out.println("groupCurrent : " + groupCurrent );
		System.out.println("groupStart : " + groupStart);
		System.out.println("groupEnd : " + groupEnd);
		System.out.println("groups[0] : " + groups[0]);
		System.out.println("groups[1] : " + groups[1]);*/
		
		return groups;
	}
}