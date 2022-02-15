package com.thisfit.shoppingmall.user.util.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	private static final Logger log = LoggerFactory.getLogger(LogoutController.class);
	
	// 로그아웃
	@GetMapping
	public String logoutGet(HttpSession session) {
		log.info("로그아웃");
		
		session.invalidate();
		
		return "redirect:/main";
	}
	
}
