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

	private final int reviewNo;
	private final int itemNo;
	private final int grade;
	
	private final String userId;
	private final String content;
	private final String reviewImg;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime updateDate;

}
