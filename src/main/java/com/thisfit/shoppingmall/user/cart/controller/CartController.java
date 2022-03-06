package com.thisfit.shoppingmall.user.cart.controller;

import com.thisfit.shoppingmall.user.cart.domain.usecase.CartUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

	public static final Logger log = LoggerFactory.getLogger(CartController.class);

	private final CartUseCase cartUseCase;
	
	// 장바구니 페이지 진입 (리스트)
	@GetMapping("/list")
	public String getCartList(String userId, Model model) {
		log.info("장바구니 페이지 진입 (리스트)");
		
		model.addAttribute("cartListInfo", cartUseCase.getCartList(userId));
		
		return "cart/cartList";
	}
	
	// 장바구니 담기
	@ResponseBody
	@PostMapping("/addItemInCart")
	public String addItemInCart(int itemNo, String userId, String selectedOptions) {
		
		return cartUseCase.addItemInCart(itemNo, userId, selectedOptions);
	}
	
	// 장바구니 상품 수량 변경
	@ResponseBody
	@PostMapping("/modifyItemQty")
	public int itemQtyModify(int qty, int cartNo) {
		cartUseCase.modifyItemQty(qty, cartNo);
		
		return qty;
	}
	
	// 장바구니 선택 상품 삭제
	@ResponseBody
	@PostMapping("/deleteItemInCart")
	public void deleteItemInCart(int cartNo) {
		cartUseCase.deleteItemInCart(cartNo);
	}
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	@ResponseBody
	@PostMapping("/deleteAllItemInCart")
	public void deleteAllItemInCart(String userId) {
		cartUseCase.deleteAllItemInCart(userId);
	}
	
}
