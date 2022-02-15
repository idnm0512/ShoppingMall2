package com.thisfit.shoppingmall.user.wishlist.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WishListRequest {

	private int wishlist_no;
	private int item_no;
	
	private String user_id;
	
}
