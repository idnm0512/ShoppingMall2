package com.thisfit.shoppingmall.user.order.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Getter
public class ItemInOrder {

	// Order Entity (ORDER_TB)
	private int orderNo;
	private int itemNo;
	private int qty;

	private String userId;
	private String opt;
	private String orderState;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime orderDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime orderCancelDate;

	// Item Entity (ITEM_TB)
	private int price;
	private int discount;

	private String name;
	private String thumbnail;

}
