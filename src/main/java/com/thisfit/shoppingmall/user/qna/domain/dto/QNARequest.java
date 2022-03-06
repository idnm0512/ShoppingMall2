package com.thisfit.shoppingmall.user.qna.domain.dto;

import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QNARequest {
	
	private int no;
	
	private String title;
	private String content;
	private String writer;

	private boolean state;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_date;

}
