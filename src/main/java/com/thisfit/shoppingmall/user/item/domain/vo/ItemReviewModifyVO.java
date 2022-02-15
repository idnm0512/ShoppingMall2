package com.thisfit.shoppingmall.user.item.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemReviewModifyVO {

	private final int review_no;
	private final int grade;
	
	private final String content;
	private final String review_img;

}
