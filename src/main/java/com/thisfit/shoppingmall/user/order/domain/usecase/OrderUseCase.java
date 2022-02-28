package com.thisfit.shoppingmall.user.order.domain.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.domain.repository.OrderGateway;
import com.thisfit.shoppingmall.user.order.domain.vo.ItemInOrderVO;
import com.thisfit.shoppingmall.user.order.domain.vo.OrderItemVO;
import lombok.RequiredArgsConstructor;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderUseCase {

	private final OrderGateway orderGateway;

	// 상품 리스트
	public List<ItemInOrderVO> getOrderItemList(String user_id, String order_state) {
		List<ItemInOrder> itemInOrderList = orderGateway.getOrderItemList(user_id, order_state);
		
		List<ItemInOrderVO> itemInOrderVOList = new ArrayList<>();
		
		for (int i = 0; i < itemInOrderList.size(); i++) {
			ItemInOrder itemInOrder = itemInOrderList.get(i);
			
			ItemInOrderVO itemInOrderVO = new ItemInOrderVO(itemInOrder.getOrder_no(),
														    itemInOrder.getItem_no(),
														    itemInOrder.getQty(),
														    itemInOrder.getPrice(),
														    itemInOrder.getDiscount(),
														    itemInOrder.getUser_id(),
														    itemInOrder.getOpt(),
														    itemInOrder.getOrder_state(),
														    itemInOrder.getThumbnail(),
														    itemInOrder.getName(),
														    itemInOrder.getOrder_date(),
														    itemInOrder.getOrder_cancel_date());
			
			itemInOrderVO.calculatePrice();
			
			itemInOrderVOList.add(itemInOrderVO);
		}
		
		return itemInOrderVOList;
	}
	
	// 상품 구매하기
	public String buyItem(int item_no, String user_id, String selected_options) {
		List<Map<String, Object>> info = JSONArray.fromObject(selected_options);
		
		for (Map<String, Object> optInfo : info) {
			OrderItemVO orderItemVO = new OrderItemVO(item_no,
													  user_id,
													  (int)optInfo.get("qty"),
													  (String)optInfo.get("name"));

			orderGateway.buyItem(orderItemVO);
		}
		
		return selected_options;
	}
	
	// 선택 상품 취소 (상태변경)
	public void cancelOrder(int order_no) {
		orderGateway.cancelOrder(order_no);
	}
	
	// 전체 상품 취소 (상태변경)
	public void cancelAllOrder(String user_id) {
		orderGateway.cancelAllOrder(user_id);
	}
	
}
