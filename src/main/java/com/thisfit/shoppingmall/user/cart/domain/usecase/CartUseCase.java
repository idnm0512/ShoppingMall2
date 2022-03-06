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
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartUseCase {

	private final CartGateway cartGateway;
	
	// 장바구니 리스트
	public List<ItemInCartVO> getCartList(String userId) {
		List<ItemInCart> itemInCartList = cartGateway.getCartList(userId);
		
		List<ItemInCartVO> itemInCartVOList = new ArrayList<>();
		
		for (int i = 0; i < itemInCartList.size(); i++) {
			ItemInCart itemInCart = itemInCartList.get(i);
			
			ItemInCartVO itemInCartVO = new ItemInCartVO(itemInCart.getCartNo(),
														 itemInCart.getItemNo(),
														 itemInCart.getQty(),
														 itemInCart.getPrice(),
														 itemInCart.getDiscount(),
														 itemInCart.getUserId(),
														 itemInCart.getOpt(),
														 itemInCart.getName(),
														 itemInCart.getThumbnail());
			
			itemInCartVO.calculatePrice();
			
			itemInCartVOList.add(itemInCartVO);
		}
		
		return itemInCartVOList;
	}
	
	// 장바구니 담기
	public String addItemInCart(int itemNo, String userId, String selectedOptions) {
		List<Map<String, Object>> info = JSONArray.fromObject(selectedOptions);
		 
		for (Map<String, Object> optInfo : info) {
			CartAddItemVO cartAddItemVO = new CartAddItemVO(itemNo,
															userId,
															(int)optInfo.get("qty"),
															(String)optInfo.get("name"));

			cartGateway.addItemInCart(cartAddItemVO);
		}
		
		return selectedOptions;
	}
	
	// 장바구니 상품 수량 변경
	public int modifyItemQty(int qty, int cartNo) {
		cartGateway.modifyItemQty(qty, cartNo);
		
		return qty;
	}
	
	// 장바구니 선택 상품 삭제
	public void deleteItemInCart(int cartNo) {
		cartGateway.deleteItemInCart(cartNo);
	}
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	public void deleteAllItemInCart(String userId) {
		cartGateway.deleteAllItemInCart(userId);
	}
	
}
