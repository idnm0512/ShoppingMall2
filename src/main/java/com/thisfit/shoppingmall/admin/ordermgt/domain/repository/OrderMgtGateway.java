package com.thisfit.shoppingmall.admin.ordermgt.domain.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import org.springframework.data.domain.Pageable;

public interface OrderMgtGateway {

	// 주문관리 리스트
	public List<ItemInOrder> getOrderMgtList(String order_state, Pageable pageable);
	
	// 총 주문수
	public int getTotalOrder(String order_state);
	
	// 선택 상품 배송완료
	public void successOrder(int order_no);
	
	// 전체 상품 배송완료
	public void successAllOrder();
	
	// 선택 상품 취소완료
	public void cancelSuccessOrder(int order_no);
	
	// 전체 상품 취소완료
	public void cancelSuccessAllOrder();

}
