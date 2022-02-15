package com.thisfit.shoppingmall.admin.commmgt.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class CommMgtModifyVO {

	private final int no;
	
	private final String title;
	private final String content;

}
