package com.thisfit.shoppingmall.user.cart.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class CartAddItemVO {

	private final int itemNo;
	private final int qty;
	
	private final String userId;
	private final String opt;

	public CartAddItemVO(int itemNo, String userId, int qty, String opt) {
		this.itemNo = itemNo;
		this.qty = qty;
		this.userId = userId;
		this.opt = opt;
	}

}
