package com.thisfit.shoppingmall.admin.ordermgt.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.admin.ordermgt.domain.repository.OrderMgtGateway;
import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.domain.vo.ItemInOrderVO;
import com.thisfit.shoppingmall.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderMgtUseCase {
	
	private final OrderMgtGateway orderMgtGateway;

	// 주문관리 리스트
	public List<ItemInOrderVO> getOrderMgtList(String order_state, Pageable pageable) {
		List<ItemInOrder> itemInOrderList = orderMgtGateway.getOrderMgtList(order_state,
																		    pageable);
		
		List<ItemInOrderVO> itemInOrderVOList = new ArrayList<>();
		
		for (int i = 0; i < itemInOrderList.size(); i++) {
			ItemInOrder itemInOrder = itemInOrderList.get(i);
			
			ItemInOrderVO itemInOrderVO = new ItemInOrderVO(itemInOrder.getOrderNo(),
														    itemInOrder.getItemNo(),
														    itemInOrder.getQty(),
														    itemInOrder.getPrice(),
														    itemInOrder.getDiscount(),
														    itemInOrder.getUserId(),
														    itemInOrder.getOpt(),
														    itemInOrder.getOrderState(),
														    itemInOrder.getThumbnail(),
														    itemInOrder.getName(),
														    itemInOrder.getOrderDate(),
														    itemInOrder.getOrderCancelDate());
			
			itemInOrderVO.calculatePrice();
			
			itemInOrderVOList.add(itemInOrderVO);
		}
		
		return itemInOrderVOList;
	}
	
	// 선택 상품 배송완료
	public void successOrder(int order_no) {
		orderMgtGateway.successOrder(order_no);
	}
	
	// 전체 상품 배송완료
	public void successAllOrder() {
		orderMgtGateway.successAllOrder();
	}
	
	// 선택 상품 취소완료
	public void cancelSuccessOrder(int order_no) {
		orderMgtGateway.cancelSuccessOrder(order_no);
	}
	
	// 전체 상품 취소완료
	public void cancelSuccessAllOrder() {
		orderMgtGateway.cancelSuccessAllOrder();
	}
	
	// 주문관리 페이징 처리
	public PageMaker pagingOrderMgt(String order_state, Pageable pageable) {
		int total = orderMgtGateway.getTotalOrder(order_state);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
