package com.thisfit.shoppingmall.user.order.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Getter
public class ItemInOrder {

	// Order Entity (ORDER_TB)
	private int order_no;
	private int item_no;
	private int qty;

	private String user_id;
	private String opt;
	private String order_state;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime order_date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime order_cancel_date;

	// Item Entity (ITEM_TB)
	private int price;
	private int discount;

	private String name;
	private String thumbnail;

}
