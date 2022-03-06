package com.thisfit.shoppingmall.user.item.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemReviewModifyVO {

	private final int reviewNo;
	private final int grade;
	
	private final String content;
	private final String reviewImg;

}
