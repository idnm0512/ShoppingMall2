package com.thisfit.shoppingmall.user.item.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ItemReviewRequest {

	private int review_no;
	private int item_no;
	private int grade;
	
	private String user_id;
	private String content;
	private String review_img;
	
	private MultipartFile review_img_file;

}
