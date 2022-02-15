package com.thisfit.shoppingmall.user.qna.domain.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class QNADetailVO {
	
	private final int no;

	private final String title;
	private final String content;
	private final String writer;

	private final boolean state;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime update_date;

}
