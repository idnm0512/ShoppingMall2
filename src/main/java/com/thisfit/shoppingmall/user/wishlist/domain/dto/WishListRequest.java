package com.thisfit.shoppingmall.user.wishlist.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WishListRequest {

	private int wishlistNo;
	private int itemNo;
	
	private String userId;
	
}
