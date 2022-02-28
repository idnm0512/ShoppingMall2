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
	public String getOrderList(String user_id, String order_state, Model model) {
		log.info("상품 주문/배송 페이지 진입");
		
		model.addAttribute("order_state", order_state);
		model.addAttribute("orderListInfo", orderUseCase.getOrderItemList(user_id, order_state));
		
		return "order/orderList";
	}
	
	// 상품 구매하기
	@ResponseBody
	@PostMapping("/buyItem")
	public String buyItem(int item_no, String user_id, String selected_options) {
		
		return orderUseCase.buyItem(item_no, user_id, selected_options);
	}
	
	// 선택 상품 취소 (상태변경)
	@ResponseBody
	@PostMapping("/cancelOrder")
	public void cancelOrder(int order_no) {
		orderUseCase.cancelOrder(order_no);
	}
	
	// 전체 상품 취소 (상태변경)
	@ResponseBody
	@PostMapping("/cancelAllOrder")
	public void cancelAllOrder(String user_id) {
		orderUseCase.cancelAllOrder(user_id);
	}
	
}
