package com.thisfit.shoppingmall.admin.ordermgt.repository;

import java.util.List;

import com.thisfit.shoppingmall.admin.ordermgt.domain.repository.OrderMgtGateway;
import com.thisfit.shoppingmall.admin.ordermgt.repository.datasource.OrderMgtJpaRepository;
import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.repository.datasource.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderMgtJpa implements OrderMgtGateway {

	@Autowired
	private OrderMgtJpaRepository orderMgtJpaRepository;
	
	// 주문관리 리스트
	@Override
	public List<ItemInOrder> getOrderMgtList(String order_state, Pageable pageable) {
		return orderMgtJpaRepository.findOrderMgtList(order_state, pageable);
	}
	
	// 총 주문수
	@Override
	public int getTotalOrder(String order_state) {
		return orderMgtJpaRepository.countByOrderState(order_state);
	}
	
	// 선택 상품 배송완료
	@Override
	@Modifying
	@Transactional
	public void successOrder(int order_no) {
		Order order = orderMgtJpaRepository.findByOrderNo(order_no);

		order.changeOrderState("배송완료");

		orderMgtJpaRepository.save(order);
	}
	
	// 전체 상품 배송완료
	@Override
	@Modifying
	@Transactional
	public void successAllOrder() {
		List<Order> orderList = orderMgtJpaRepository.findByOrderState("배송준비중");

		for (int i = 0; i < orderList.size(); i ++) {
			Order order = orderList.get(i);

			order.changeOrderState("배송완료");

			orderMgtJpaRepository.save(order);
		}
	}
	
	// 선택 상품 취소완료
	@Override
	@Modifying
	@Transactional
	public void cancelSuccessOrder(int order_no) {
		Order order = orderMgtJpaRepository.findByOrderNo(order_no);

		order.changeOrderState("취소완료");

		orderMgtJpaRepository.save(order);
	}
	
	// 전체 상품 취소완료
	@Override
	@Modifying
	@Transactional
	public void cancelSuccessAllOrder() {
		List<Order> orderList = orderMgtJpaRepository.findByOrderState("주문취소");

		for (int i = 0; i < orderList.size(); i ++) {
			Order order = orderList.get(i);

			order.changeOrderState("취소완료");

			orderMgtJpaRepository.save(order);
		}
	}
	
}
