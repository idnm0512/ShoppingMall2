package com.thisfit.shoppingmall.user.cart.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemInCart {

	// Cart Entity (CART_TB)
	private int cartNo;
	private int itemNo;
	private int qty;

	private String userId;
	private String opt;

	// Item Entity (ITEM_TB)
	private int price;
	private int discount;

	private String name;
	private String thumbnail;

}
