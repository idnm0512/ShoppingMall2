package com.thisfit.shoppingmall.user.login.controller;

import javax.servlet.http.HttpSession;

import com.thisfit.shoppingmall.user.login.domain.dto.LoginRequest;
import com.thisfit.shoppingmall.user.login.domain.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private final LoginUseCase loginUseCase;
	
	// 로그인 페이지 진입
	@GetMapping
	public String loginGet() {
		log.info("로그인 페이지 진입");
		
		return "login/login";
	}
	
	// 로그인 (아이디, 패스워드 체크)
	@ResponseBody
	@PostMapping
	public String loginPost(LoginRequest loginRequest, HttpSession session) {
		log.info("로그인");
		
		String id = loginUseCase.getUserId(loginRequest);
		
		if (id != "") {
			session.setAttribute("id", id);
		}
		
		return id;
	}
	
	// 카카오 로그인
	@GetMapping("/kakaoLogin")
	public String kakaoLoginGet(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {
		loginUseCase.kakaoLogin(code, session);
		
		return "redirect:/main";
	}
	
	// 아이디 찾기 페이지 진입
	@GetMapping("/findId")
	public String findIdGet() {
		log.info("아이디 찾기 페이지 진입");
		
		return "login/findId";
	}
	
	// 아이디 찾기
	@PostMapping("/findId")
	public String findIdPost(String name, String email, Model model, RedirectAttributes rttr) {
		return loginUseCase.findId(name, email, model, rttr);
	}
	
	// 비밀번호 찾기 페이지 진입
	@GetMapping("/findPwd")
	public String findPwdGet() {
		log.info("비밀번호 찾기 페이지 진입");
		
		return "login/findPwd";
	}
	
	// 비밀번호 찾기
	@PostMapping("/findPwd")
	public String findPwdPost(String id, String email, Model model, RedirectAttributes rttr) {
		return loginUseCase.findPwd(id, email, model, rttr);
	}
	
}
