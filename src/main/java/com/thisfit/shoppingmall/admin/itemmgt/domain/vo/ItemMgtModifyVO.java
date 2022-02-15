package com.thisfit.shoppingmall.admin.itemmgt.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemMgtModifyVO {

	private final int no;
	private final int price;
	private final int discount;
	
	private final String category;
	private final String category2;
	private final String name;
	private final String content;
	private final String thumbnail;

}
