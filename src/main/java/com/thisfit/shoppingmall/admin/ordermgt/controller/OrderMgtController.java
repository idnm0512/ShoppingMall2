package com.thisfit.shoppingmall.admin.ordermgt.controller;

import com.thisfit.shoppingmall.admin.ordermgt.domain.usecase.OrderMgtUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderMgt")
public class OrderMgtController {

	public static final Logger log = LoggerFactory.getLogger(OrderMgtController.class);
	
	@Autowired
	private OrderMgtUseCase orderMgtUseCase;
	
	// 주문관리 페이지 진입
	@GetMapping("/list")
	public String getOrderMgtList(@PageableDefault(size = 20, sort = "orderNo", direction = Sort.Direction.DESC) Pageable pageable,
								  String order_state, Model model) {

		model.addAttribute("order_state", order_state);
		model.addAttribute("orderListInfo", orderMgtUseCase.getOrderMgtList(order_state, pageable));
		model.addAttribute("pageMaker", orderMgtUseCase.pagingOrderMgt(order_state, pageable));

		return "/admin/orderMgt/orderMgtList";
	}
	
	// 선택 상품 배송완료
	@ResponseBody
	@PostMapping("successOrder")
	public void successOrder(int order_no) {
		orderMgtUseCase.successOrder(order_no);
	}
	
	// 전체 상품 배송완료
	@ResponseBody
	@PostMapping("successAllOrder")
	public void successAllOrder() {
		orderMgtUseCase.successAllOrder();
	}
	
	// 선택 상품 취소완료
	@ResponseBody
	@PostMapping("cancelSuccessOrder")
	public void cancelSuccessOrder(int order_no) {
		orderMgtUseCase.cancelSuccessOrder(order_no);
	}
	
	// 전체 상품 취소완료
	@ResponseBody
	@PostMapping("cancelSuccessAllOrder")
	public void cancelSuccessAllOrder() {
		orderMgtUseCase.cancelSuccessAllOrder();
	}
	
}
