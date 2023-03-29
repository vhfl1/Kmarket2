package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.ReviewVO;

@Mapper
@Repository
public interface ProductDAO {
	public List<ProductVO> selectProductByParam(String param);
	public List<ProductVO> selectProducts(String arg0, String arg1,String arg2,int arg3);
	public List<ProductVO> selectProductByKeyword(String keyword);
	public int selectCountTotal(String arg0, String arg1);
	public int selectProductByKeywordTotal(String keyword);
	public int selectReviewCountTotal(String param1,int param2);
	public ProductVO selectProduct(String param1);
	public List<ReviewVO> selectReviews(String param1);
	public int addCart(CartVO cart);
	public CartVO checkCart(CartVO cart);
	public List<CartVO> selectCarts(String uid);
	public int deleteCart(String cartNo);
	public CartVO selectOrder(String cartNo);
}
