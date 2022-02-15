package com.thisfit.shoppingmall.admin.itemmgt.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemMgtOptInsertVO {
	
	private final int item_no;
	private final int qty;
	
	private final String color;
	private final String size;

}
