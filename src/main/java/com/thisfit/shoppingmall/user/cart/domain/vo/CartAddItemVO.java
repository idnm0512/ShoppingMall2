package com.thisfit.shoppingmall.user.cart.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class CartAddItemVO {

	private final int item_no;
	private final int qty;
	
	private final String user_id;
	private final String opt;

	public CartAddItemVO(int item_no, String user_id, int qty, String opt) {
		this.item_no = item_no;
		this.qty = qty;
		this.user_id = user_id;
		this.opt = opt;
	}

}
