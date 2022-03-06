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
	public List<ItemInOrderVO> getOrderItemList(String userId, String orderState) {
		List<ItemInOrder> itemInOrderList = orderGateway.getOrderItemList(userId, orderState);
		
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
	
	// 상품 구매하기
	public String buyItem(int itemNo, String userId, String selectedOptions) {
		List<Map<String, Object>> info = JSONArray.fromObject(selectedOptions);
		
		for (Map<String, Object> optInfo : info) {
			OrderItemVO orderItemVO = new OrderItemVO(itemNo,
												      userId,
													  (int)optInfo.get("qty"),
													  (String)optInfo.get("name"));

			orderGateway.buyItem(orderItemVO);
		}
		
		return selectedOptions;
	}
	
	// 선택 상품 취소 (상태변경)
	public void cancelOrder(int orderNo) {
		orderGateway.cancelOrder(orderNo);
	}
	
	// 전체 상품 취소 (상태변경)
	public void cancelAllOrder(String userId) {
		orderGateway.cancelAllOrder(userId);
	}
	
}
