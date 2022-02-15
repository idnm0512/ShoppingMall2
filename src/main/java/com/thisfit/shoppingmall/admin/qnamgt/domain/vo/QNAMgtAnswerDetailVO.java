package com.thisfit.shoppingmall.admin.qnamgt.domain.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class QNAMgtAnswerDetailVO {
	
	private final int re_no;

	private final String re_title;
	private final String re_content;
	private final String re_writer;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime re_update_date;

}
