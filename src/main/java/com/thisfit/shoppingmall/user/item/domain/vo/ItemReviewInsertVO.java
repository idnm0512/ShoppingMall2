package com.thisfit.shoppingmall.user.item.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemReviewInsertVO {
	
	private final int review_no;
	private final int item_no;
	private final int grade;
	
	private final String user_id;
	private final String content;
	private final String review_img;

}
