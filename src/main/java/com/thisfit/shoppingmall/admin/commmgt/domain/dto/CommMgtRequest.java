package com.thisfit.shoppingmall.admin.commmgt.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class CommMgtRequest {

	private int no;
	private String title;
	private String content;
	private String writer;
	private String category;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_date;

}
