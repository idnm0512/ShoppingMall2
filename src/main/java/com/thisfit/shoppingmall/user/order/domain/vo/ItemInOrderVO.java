package com.thisfit.shoppingmall.user.order.domain.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemInOrderVO {
	
	private int discountedPrice;

	private final int orderNo;
	private final int itemNo;
	private final int qty;
	private final int price;
	private final int discount;
	
	private final String userId;
	private final String opt;
	private final String orderState;
	private final String thumbnail;
	private final String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime orderDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime orderCancelDate;

	// 할인율이 계산된 가격을 구하기 위한 메서드
	public void calculatePrice() {
		this.discountedPrice = this.price - (this.price * this.discount / 100);
	}
	
}
