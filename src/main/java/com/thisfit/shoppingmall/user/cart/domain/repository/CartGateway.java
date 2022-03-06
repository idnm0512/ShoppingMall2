package com.thisfit.shoppingmall.user.cart.domain.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.cart.domain.dto.ItemInCart;
import com.thisfit.shoppingmall.user.cart.domain.vo.CartAddItemVO;

public interface CartGateway {

	// 장바구니 리스트
	public List<ItemInCart> getCartList(String userId);
	
	// 장바구니 담기
	public void addItemInCart(CartAddItemVO cartAddItemVO);
	
	// 장바구니 상품 수량 변경
	public void modifyItemQty(int qty, int cartNo);
	
	// 장바구니 선택 상품 삭제
	public void deleteItemInCart(int cartNo);
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	public void deleteAllItemInCart(String userId);
	
}
