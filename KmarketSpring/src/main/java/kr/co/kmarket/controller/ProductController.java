package kr.co.kmarket.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.ReviewVO;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private MemberService service1;
	
	@GetMapping("product/list")
	public String list(String arg0, String arg1, String arg2, String pg, Model model) {
		int currentPage = service.getCurrentPage(pg);
        int arg3 = service.getLimitStart(currentPage);

        int total = service.selectCountTotal(arg0,arg1);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, arg3);
        int groups[] = service.getPageGroup(currentPage, lastPageNum);

		List<ProductVO> products = service.selectProducts(arg0,arg1,arg2,arg3);
		
		model.addAttribute("arg0", arg0);
		model.addAttribute("arg1", arg1);
		model.addAttribute("arg2", arg2);
		model.addAttribute("cate1", arg0);
		model.addAttribute("cate2", arg1);
		model.addAttribute("pg", pg);
		model.addAttribute("groups", groups);
		model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		
		return "product/list";
	}
	
	//상품검색
	@GetMapping("product/search")
	public String searchProduct(String arg0, String arg1, String arg2, Model model,String keyword) {
		
		List<ProductVO> list = service.selectProductByKeyword(keyword);
		int total = service.selectProductByKeywordTotal(keyword);
		
		model.addAttribute("keyword",keyword);
		model.addAttribute("list",list);
		model.addAttribute("total",total);
		model.addAttribute("arg0", arg0);
		model.addAttribute("arg1", arg1);
		model.addAttribute("arg2", arg2);
		model.addAttribute("cate1", arg0);
		model.addAttribute("cate2", arg1);
		
		return "product/search";
	}
	
	@GetMapping("product/view")
	public String view(String arg0, String arg1, String arg2, String pg, String param1, Model model) {
		
		ProductVO prod = service.selectProduct(param1);
		List<ReviewVO> reviews = service.selectReviews(param1);
		
		model.addAttribute("arg0", arg0);
		model.addAttribute("arg1", arg1);
		model.addAttribute("arg2", arg2);
		model.addAttribute("pg", pg);
		model.addAttribute("prod", prod);
		model.addAttribute("reviews", reviews);
		model.addAttribute("param1", param1);
		
		return "product/view";
	}
	
	@PostMapping("product/cart/add")
	@ResponseBody
	public String addCart(CartVO cart, HttpServletRequest req) {
		//로그인 체크
		HttpSession session = req.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		if(mvo == null) {
			return "5";
		}
		//장바구니 추가
		int result = service.addCart(cart);
		
		return result+"";
	}
	
	@GetMapping("product/cart")
	public String cart(Model model, String uid, String arg0) {
		List<CartVO> carts = service.selectCarts(uid);
		
		model.addAttribute("arg0",arg0);
		model.addAttribute("carts",carts);
		
		return "product/cart";
	}

	@PostMapping("product/cart/delete")
	@ResponseBody
	public String deleteCart(@RequestParam(value="checkBoxArr[]")List<String> checkBoxArr) {
		
		int result = 0;
		
		for(int i = 0; i < checkBoxArr.size(); i++) {
			service.deleteCart(checkBoxArr.get(i));
		}	
		
		return result + "";
	}
	
	@GetMapping("product/order")
	public String orderList(Model model, String param1, String count, String uid) {
		
		ProductVO product = service.selectProduct(param1);
		List<CartVO> carts = service.selectCarts(uid);
		MemberVO user = service1.selectUser(uid);
		
		model.addAttribute("product",product);
		model.addAttribute("carts",carts);
		model.addAttribute("user",user);
		model.addAttribute("count",count);
		return "product/order";
	}

	/*@PostMapping("product/order")
	public String orderList(Model model, @RequestParam(value="checkBoxArr[]")List<String> checkBoxArr) {
		List<CartVO> cartList = new ArrayList<>();
		
		for(int i = 0; i < checkBoxArr.size(); i++) {
			
			CartVO cart = service.selectOrder(checkBoxArr.get(i));
			cartList.add(cart);
			
		}
		
		String uid = (String) cartList.get(0).getUid();
		MemberVO user = service1.selectUser(uid);
		
		model.addAttribute("cartList", cartList);
		model.addAttribute("user", user);
		
		return "product/order";
		
		System.out.println(checkBoxArr);
		//System.out.println(checkBoxArr.get(0));
		
		List<CartVO> carts = new ArrayList<>();
		
		for(int i = 0; i < checkBoxArr.length; i++) {
			
			CartVO cart = service.selectOrder(checkBoxArr[i]);
			carts.add(cart);
			
		}
		
		String uid = (String) carts.get(0).getUid();
		MemberVO user = service1.selectUser(uid);
		
		

		System.out.println("user 정보 : "+user.getUid());
		System.out.println("uid 정보 : "+uid);
		System.out.println("post ---------");

		model.addAttribute("carts", carts);
		model.addAttribute("user", user);
		
		return "product/order";
		
		System.out.println(checkBoxArr);
		System.out.println(checkBoxArr.get(0));
		
		List<CartVO> carts = new ArrayList<>();
		
		Map<String, Object> cartList = new HashMap<String, Object>();
		List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i < checkBoxArr.size(); i++) {
			
			CartVO cart = service.selectOrder(checkBoxArr.get(i));
			
			carts.add(cart);
			
			cartList.put("cartNo", carts.get(i).getCartNo());
			cartList.put("uid", carts.get(i).getUid());
			cartList.put("prodNo", carts.get(i).getProdNo());
			cartList.put("count", carts.get(i).getCount());
			cartList.put("price", carts.get(i).getPrice());
			cartList.put("discount", carts.get(i).getDiscount());
			cartList.put("disPrice", carts.get(i).getDisPrice());
			cartList.put("point", carts.get(i).getPoint());
			cartList.put("delivery", carts.get(i).getDelivery());
			cartList.put("total", carts.get(i).getTotal());
			cartList.put("rdate", carts.get(i).getRdate());
			cartList.put("prodName", carts.get(i).getProdName());
			cartList.put("descript", carts.get(i).getDescript());
			cartList.put("thumb1", carts.get(i).getThumb1());
			
			orderList.add(cartList);
		}
		
		String uid = (String) orderList.get(0).get("uid");
		MemberVO user = service1.selectUser(uid);
		
		
		
		System.out.println("user 정보 : "+user.getUid());
		System.out.println("uid 정보 : "+uid);
		System.out.println(orderList);
		System.out.println(orderList.get(0));
		System.out.println(orderList.get(0).get("uid"));
		System.out.println("post ---------");
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("user", user);
		
		return orderList;
		
	}*/

}