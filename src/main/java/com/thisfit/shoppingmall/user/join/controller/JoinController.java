package com.thisfit.shoppingmall.user.join.controller;

import com.thisfit.shoppingmall.user.join.domain.dto.JoinRequest;
import com.thisfit.shoppingmall.user.join.domain.usecase.JoinUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/join")
public class JoinController {
	
	private static final Logger log = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private JoinUseCase joinUseCase;
	
	// 회원가입 페이지 진입
	@GetMapping
	public String joinGet() {
		log.info("회원가입 페이지 진입");
		
		return "/join/join";
	}
	
	// 회원가입
	@PostMapping
	public String joinPost(JoinRequest joinRequest, RedirectAttributes rttr) {
		joinUseCase.join(joinRequest);
		
		rttr.addFlashAttribute("result", "join success");
		
		return "redirect:/login";
	}
	
	// 아이디 중복확인
	@ResponseBody
	@PostMapping("/idCheck")
	public String idCheck(String id) {
		return joinUseCase.idCheck(id);
	}

}
