package com.thisfit.shoppingmall.user.community.domain.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class CommunityDetailVO {
	
	private final int no;
	
	private final String category;
	private final String title;
	private final String content;
	private final String writer;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime update_date;

}
