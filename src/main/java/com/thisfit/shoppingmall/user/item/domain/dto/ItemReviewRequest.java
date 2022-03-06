package com.thisfit.shoppingmall.user.item.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemReviewRequest {

	private int reviewNo;
	private int itemNo;
	private int grade;
	
	private String userId;
	private String content;
	private String reviewImg;
	
	private MultipartFile reviewImgFile;

}
