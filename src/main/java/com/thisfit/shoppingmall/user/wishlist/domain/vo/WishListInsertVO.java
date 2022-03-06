package com.thisfit.shoppingmall.user.wishlist.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class WishListInsertVO {

	private final int itemNo;
	
	private final String userId;

}
