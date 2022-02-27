package com.thisfit.shoppingmall.util.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jusoPopup")
public class JusoPopupController {
	
	private static final Logger log = LoggerFactory.getLogger(JusoPopupController.class);

	// 주소 팝업창 띄우기
	@GetMapping
	public String jusoPopupGet() {
		log.info("주소 팝업창 띄우기");
		
		return "juso/jusoPopup";
	}
	
	// 현재 페이지에 주소 입력
	@PostMapping
	public String jusoPopupPost() {
		log.info("현재 페이지에 주소 입력");
		
		return "juso/jusoPopup";
	}
	
}
