package com.thisfit.shoppingmall.user.qna.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Getter
public class QNAWithAnswer {

	// QNA Entity (QNA_TB)
	private int no;

	private String title;
	private String writer;

	private boolean state;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime update_date;

	// QNAMgtAnswer Entity (QNA_ANSWER_TB)
	private int re_no;

	private String re_title;
	private String re_writer;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime re_update_date;

}
