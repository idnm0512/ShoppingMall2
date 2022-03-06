package com.thisfit.shoppingmall.user.wishlist.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemInWishList {

	// WishList Entity (WISHLIST_TB)
	private int wishlistNo;
	private int itemNo;

	private String userId;

	// Item Entity (ITEM_TB)
	private int price;
	private int discount;

	private String name;
	private String thumbnail;

}
