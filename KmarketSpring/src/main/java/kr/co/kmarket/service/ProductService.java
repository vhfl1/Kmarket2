package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.ReviewVO;

@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
	
	public List<ProductVO> selectProductByParam(String param) {
		return dao.selectProductByParam(param);
	}
	public List<ProductVO> selectProducts(String arg0, String arg1, String arg2,int arg3) {
		return dao.selectProducts(arg0,arg1,arg2,arg3);
	}
	public int selectCountTotal(String arg0, String arg1){
        return dao.selectCountTotal(arg0,arg1);
    }
	public int selectReviewCountTotal(String param1,int param2){
		return dao.selectReviewCountTotal(param1,param2);
	}
	public ProductVO selectProduct(String param1) {
		return dao.selectProduct(param1);
	}
	public List<ReviewVO> selectReviews(String param1) {
		return dao.selectReviews(param1);
	}
	public List<ProductVO> selectProductByKeyword(String keyword){
		return dao.selectProductByKeyword(keyword);
	}
	public int selectProductByKeywordTotal(String keyword) {
		return dao.selectProductByKeywordTotal(keyword);
	}

	
	//장바구니 추가
	public int addCart(CartVO cart) {
		return dao.addCart(cart);
	}
	
	//장바구니 목록
	public List<CartVO> selectCarts(String uid){
		return dao.selectCarts(uid);
	}
	
	//장바구니 삭제
	public int deleteCart(String cartNo) {
		int result = dao.deleteCart(cartNo);
		return result;
	}
	
	//장바구니에서 주문목록 불러오기
	public CartVO selectOrder(String cartNo){
		return dao.selectOrder(cartNo);
	}

	//현재 페이지 번호
    public int getCurrentPage(String pg){
        int currentPage =1;

        if(pg != null){
            currentPage = Integer.parseInt(pg);
        }

        return currentPage;
    }

    //페이지 시작값
    public int getLimitStart(int currentPage){
        return (currentPage -1) * 10;
    }

    //마지막 페이지 번호
    public int getLastPageNum(int total){
        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else{
            lastPageNum = total / 10 + 1;
        }

        return lastPageNum;
    }

    //페이지 시작번호
    public int getPageStartNum(int total, int arg3){
        return  total - arg3;
    }

    //페이지 그룹
    public int[] getPageGroup(int  currentPage, int lastPageNum){
        int groupCurrent = (int) Math.ceil(currentPage / 10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if(groupEnd > lastPageNum){
            groupEnd = lastPageNum;
        }

        int[] groups = {groupStart, groupEnd};

        return groups;
    }

}
	

