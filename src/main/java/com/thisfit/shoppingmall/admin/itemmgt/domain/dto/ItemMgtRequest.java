package com.thisfit.shoppingmall.admin.itemmgt.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ItemMgtRequest {

	private int no;
	private int price;
	private int discount;
	
	private String category;
	private String category2;
	private String name;
	private String thumbnail;
	private String content;
	
	private MultipartFile thumbnail_file;

}
