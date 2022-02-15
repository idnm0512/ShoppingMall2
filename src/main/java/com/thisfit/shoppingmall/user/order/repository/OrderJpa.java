package com.thisfit.shoppingmall.user.order.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.domain.repository.OrderGateway;
import com.thisfit.shoppingmall.user.order.domain.vo.OrderItemVO;
import com.thisfit.shoppingmall.user.order.repository.datasource.Order;
import com.thisfit.shoppingmall.user.order.repository.datasource.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderJpa implements OrderGateway {

	@Autowired
	private OrderJpaRepository orderJpaRepository;
	
	// 상품 주문 리스트
	@Override
	public List<ItemInOrder> getOrderItemList(String user_id, String order_state) {
		return orderJpaRepository.findOrderItemList(user_id, order_state);
	}
	
	// 상품 구매하기
	@Override
	@Modifying
	@Transactional
	public void buyItem(OrderItemVO orderItemVO) {
		Order order = Order.builder()
						   .itemNo(orderItemVO.getItem_no())
						   .qty(orderItemVO.getQty())
						   .userId(orderItemVO.getUser_id())
						   .opt(orderItemVO.getOpt())
						   .orderState(orderItemVO.getOrder_state())
						   .build();

		orderJpaRepository.save(order);
	}
	
	// 선택 상품 주문취소 (상태변경)
	@Override
	@Modifying
	@Transactional
	public void cancelOrder(int order_no) {
		Order order = orderJpaRepository.findByOrderNo(order_no);

		order.changeOrderState("주문취소");

		orderJpaRepository.save(order);
	}
	
	// 전체 상품 주문취소 (상태변경)
	@Override
	@Modifying
	@Transactional
	public void cancelAllOrder(String user_id) {
		List<Order> orderList = orderJpaRepository.findByUserIdAndOrderState(user_id, "배송준비중");

		for (int i = 0; i < orderList.size(); i++) {
			Order order = orderList.get(i);

			order.changeOrderState("주문취소");

			orderJpaRepository.save(order);
		}
	}
	
}
