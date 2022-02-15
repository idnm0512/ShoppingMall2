package com.thisfit.shoppingmall.user.item.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemVO {
	
	private int discounted_price;

	private final int no;
	private final int price;
	private final int discount;
	
	private final String category;
	private final String category2;
	private final String name;
	private final String thumbnail;
	private final String content;

	// 할인율이 계산된 가격을 구하기 위한 메서드
	public void calculatePrice() {
		this.discounted_price = this.price - (this.price * this.discount / 100);
	}
	
}
