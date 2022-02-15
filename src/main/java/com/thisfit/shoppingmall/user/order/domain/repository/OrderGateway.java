package com.thisfit.shoppingmall.user.order.domain.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.domain.vo.OrderItemVO;

public interface OrderGateway {
	
	// 상품 주문 리스트
	public List<ItemInOrder> getOrderItemList(String user_id, String order_state);
	
	// 상품 구매하기
	public void buyItem(OrderItemVO orderItemVO);
	
	// 선택 상품 주문취소 (상태변경)
	public void cancelOrder(int order_no);
	
	// 전체 상품 주문취소 (상태변경)
	public void cancelAllOrder(String user_id);
	
}
