package com.thisfit.shoppingmall.user.cart.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemInCartVO {
	
	private int discounted_price;
	
	private final int cart_no;
	private final int item_no;
	private final int qty;
	private final int price;
	private final int discount;
	
	private final String user_id;
	private final String opt;
	private final String name;
	private final String thumbnail;

	// 할인율이 계산된 가격을 구하기 위한 메서드
	public void calculatePrice() {
		this.discounted_price = this.price - (this.price * this.discount / 100);
	}

}
