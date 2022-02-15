package com.thisfit.shoppingmall.user.order.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class OrderItemVO {

	private final int item_no;
	private final int qty;
	
	private final String user_id;
	private final String opt;
	private final String order_state;

	public OrderItemVO(int item_no, String user_id, int qty, String opt) {
		this.item_no = item_no;
		this.qty = qty;
		this.user_id = user_id;
		this.opt = opt;
		this.order_state = "배송준비중";
	}

}
