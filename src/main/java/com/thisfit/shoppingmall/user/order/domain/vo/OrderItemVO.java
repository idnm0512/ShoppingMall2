package com.thisfit.shoppingmall.user.order.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class OrderItemVO {

	private final int itemNo;
	private final int qty;
	
	private final String userId;
	private final String opt;
	private final String orderState;

	public OrderItemVO(int itemNo, String userId, int qty, String opt) {
		this.itemNo = itemNo;
		this.qty = qty;
		this.userId = userId;
		this.opt = opt;
		this.orderState = "배송준비중";
	}

}
