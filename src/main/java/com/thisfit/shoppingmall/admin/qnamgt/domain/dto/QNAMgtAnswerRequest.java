package com.thisfit.shoppingmall.admin.qnamgt.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class QNAMgtAnswerRequest {

	private int re_no;
	private int qno;

	private String re_title;
	private String re_content;
	private String re_writer;

	private boolean state;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date re_regdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date re_update_date;

}
