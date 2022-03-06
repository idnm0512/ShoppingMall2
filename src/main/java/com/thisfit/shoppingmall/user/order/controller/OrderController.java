package com.thisfit.shoppingmall.user.order.controller;

import com.thisfit.shoppingmall.user.order.domain.usecase.OrderUseCase;
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
@RequestMapping("/order")
public class OrderController {

	public static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private final OrderUseCase orderUseCase;
	
	// 상품 주문/배송 페이지 진입
	@GetMapping("/list")
	public String getOrderList(String userId, String orderState, Model model) {
		log.info("상품 주문/배송 페이지 진입");
		
		model.addAttribute("orderState", orderState);
		model.addAttribute("orderListInfo", orderUseCase.getOrderItemList(userId, orderState));
		
		return "order/orderList";
	}
	
	// 상품 구매하기
	@ResponseBody
	@PostMapping("/buyItem")
	public String buyItem(int itemNo, String userId, String selectedOptions) {
		
		return orderUseCase.buyItem(itemNo, userId, selectedOptions);
	}
	
	// 선택 상품 취소 (상태변경)
	@ResponseBody
	@PostMapping("/cancelOrder")
	public void cancelOrder(int orderNo) {
		orderUseCase.cancelOrder(orderNo);
	}
	
	// 전체 상품 취소 (상태변경)
	@ResponseBody
	@PostMapping("/cancelAllOrder")
	public void cancelAllOrder(String userId) {
		orderUseCase.cancelAllOrder(userId);
	}
	
}
