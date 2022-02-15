package com.thisfit.shoppingmall.user.cart.domain.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thisfit.shoppingmall.user.cart.domain.dto.ItemInCart;
import com.thisfit.shoppingmall.user.cart.domain.repository.CartGateway;
import com.thisfit.shoppingmall.user.cart.domain.vo.CartAddItemVO;
import com.thisfit.shoppingmall.user.cart.domain.vo.ItemInCartVO;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartUseCase {

	private final CartGateway cartGateway;
	
	// 장바구니 리스트
	public List<ItemInCartVO> getCartList(String user_id) {
		List<ItemInCart> itemInCartList = cartGateway.getCartList(user_id);
		
		List<ItemInCartVO> itemInCartVOList = new ArrayList<>();
		
		for (int i = 0; i < itemInCartList.size(); i++) {
			ItemInCart itemInCart = itemInCartList.get(i);
			
			ItemInCartVO itemInCartVO = new ItemInCartVO(itemInCart.getCart_no(),
														 itemInCart.getItem_no(),
														 itemInCart.getQty(),
														 itemInCart.getPrice(),
														 itemInCart.getDiscount(),
														 itemInCart.getUser_id(),
														 itemInCart.getOpt(),
														 itemInCart.getName(),
														 itemInCart.getThumbnail());
			
			itemInCartVO.calculatePrice();
			
			itemInCartVOList.add(itemInCartVO);
		}
		
		return itemInCartVOList;
	}
	
	// 장바구니 담기
	public String addItemInCart(int item_no, String user_id, String selected_options) {
		List<Map<String, Object>> info = JSONArray.fromObject(selected_options);
		 
		for (Map<String, Object> optInfo : info) {
			CartAddItemVO cartAddItemVO = new CartAddItemVO(item_no,
															user_id,
															(int)optInfo.get("qty"),
															(String)optInfo.get("name"));

			cartGateway.addItemInCart(cartAddItemVO);
		}
		
		return selected_options;
	}
	
	// 장바구니 상품 수량 변경
	public int modifyItemQty(int qty, int cart_no) {
		cartGateway.modifyItemQty(qty, cart_no);
		
		return qty;
	}
	
	// 장바구니 선택 상품 삭제
	public void deleteItemInCart(int cart_no) {
		cartGateway.deleteItemInCart(cart_no);
	}
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	public void deleteAllItemInCart(String user_id) {
		cartGateway.deleteAllItemInCart(user_id);
	}
	
}
