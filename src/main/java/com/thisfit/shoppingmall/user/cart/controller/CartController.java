package com.thisfit.shoppingmall.user.cart.controller;

import com.thisfit.shoppingmall.user.cart.domain.usecase.CartUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController {

	public static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartUseCase cartUseCase;
	
	// 장바구니 페이지 진입 (리스트)
	@GetMapping("/list")
	public String getCartList(String user_id, Model model) {
		log.info("장바구니 페이지 진입 (리스트)");
		
		model.addAttribute("cartListInfo", cartUseCase.getCartList(user_id));
		
		return "cart/cartList";
	}
	
	// 장바구니 담기
	@ResponseBody
	@PostMapping("/addItemInCart")
	public String addItemInCart(int item_no, String user_id, String selected_options) {
		
		return cartUseCase.addItemInCart(item_no, user_id, selected_options);
	}
	
	// 장바구니 상품 수량 변경
	@ResponseBody
	@PostMapping("/modifyItemQty")
	public int itemQtyModify(int qty, int cart_no) {
		cartUseCase.modifyItemQty(qty, cart_no);
		
		return qty;
	}
	
	// 장바구니 선택 상품 삭제
	@ResponseBody
	@PostMapping("/deleteItemInCart")
	public void deleteItemInCart(int cart_no) {
		cartUseCase.deleteItemInCart(cart_no);
	}
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	@ResponseBody
	@PostMapping("/deleteAllItemInCart")
	public void deleteAllItemInCart(String user_id) {
		cartUseCase.deleteAllItemInCart(user_id);
	}
	
}
