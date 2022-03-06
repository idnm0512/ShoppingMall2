package com.thisfit.shoppingmall.user.order.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.domain.repository.OrderGateway;
import com.thisfit.shoppingmall.user.order.domain.vo.OrderItemVO;
import com.thisfit.shoppingmall.user.order.repository.datasource.Order;
import com.thisfit.shoppingmall.user.order.repository.datasource.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderJpa implements OrderGateway {

	private final OrderJpaRepository orderJpaRepository;
	
	// 상품 주문 리스트
	@Override
	public List<ItemInOrder> getOrderItemList(String userId, String orderState) {
		return orderJpaRepository.findOrderItemList(userId, orderState);
	}
	
	// 상품 구매하기
	@Override
	@Modifying
	@Transactional
	public void buyItem(OrderItemVO orderItemVO) {
		Order order = Order.builder()
						   .itemNo(orderItemVO.getItemNo())
						   .qty(orderItemVO.getQty())
						   .userId(orderItemVO.getUserId())
						   .opt(orderItemVO.getOpt())
						   .orderState(orderItemVO.getOrderState())
						   .build();

		orderJpaRepository.save(order);
	}
	
	// 선택 상품 주문취소 (상태변경)
	@Override
	@Modifying
	@Transactional
	public void cancelOrder(int orderNo) {
		Order order = orderJpaRepository.findByOrderNo(orderNo);

		order.changeOrderState("주문취소");

		orderJpaRepository.save(order);
	}
	
	// 전체 상품 주문취소 (상태변경)
	@Override
	@Modifying
	@Transactional
	public void cancelAllOrder(String userId) {
		List<Order> orderList = orderJpaRepository.findByUserIdAndOrderState(userId, "배송준비중");

		for (int i = 0; i < orderList.size(); i++) {
			Order order = orderList.get(i);

			order.changeOrderState("주문취소");

			orderJpaRepository.save(order);
		}
	}
	
}
