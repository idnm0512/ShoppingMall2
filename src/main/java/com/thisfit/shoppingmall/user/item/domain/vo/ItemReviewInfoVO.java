package com.thisfit.shoppingmall.user.item.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class ItemReviewInfoVO {

	private final int review_no;
	private final int item_no;
	private final int grade;
	
	private final String user_id;
	private final String content;
	private final String review_img;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime update_date;

}
