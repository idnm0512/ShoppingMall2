package com.thisfit.shoppingmall.user.wishlist.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemInWishList {

	// WishList Entity (WISHLIST_TB)
	private int wishlist_no;
	private int item_no;

	private String user_id;

	// Item Entity (ITEM_TB)
	private int price;
	private int discount;

	private String name;
	private String thumbnail;

}
