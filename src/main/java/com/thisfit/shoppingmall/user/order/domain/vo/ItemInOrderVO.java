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
	
	private int discounted_price;

	private final int order_no;
	private final int item_no;
	private final int qty;
	private final int price;
	private final int discount;
	
	private final String user_id;
	private final String opt;
	private final String order_state;
	private final String thumbnail;
	private final String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime order_date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime order_cancel_date;

	// 할인율이 계산된 가격을 구하기 위한 메서드
	public void calculatePrice() {
		this.discounted_price = this.price - (this.price * this.discount / 100);
	}
	
}
