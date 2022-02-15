package com.thisfit.shoppingmall.admin.itemmgt.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemMgtOptInfoVO {

	private final int opt_no;
	private final int qty;
	
	private final String color;
	private final String size;

}
