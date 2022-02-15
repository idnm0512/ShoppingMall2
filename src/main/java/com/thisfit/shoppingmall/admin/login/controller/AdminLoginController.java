package com.thisfit.shoppingmall.admin.login.controller;

import javax.servlet.http.HttpSession;

import com.thisfit.shoppingmall.admin.login.domain.usecase.AdminLoginUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adminLogin")
public class AdminLoginController {
	
	/*
	 * 스프링 MVC의 컨트롤러 핸들러 함수에 리턴값이 없는 경우,
	 * 먼저 요청된 URL에 대한 View 이름이 있는지 찾아보고,
	 * View 이름도 없다면 리소스 핸들러로 위임한다.
	 * 그래도 없다면 디스패처 서블릿으로 위임되고 404 응답으로 처리된다.
	 * 
	 * 그렇다면 요청하는 URL과 View의 이름이 같은 상황에선 굳이 return값을 쓰지 않아도 될까??
	 * 답은 No.
	 * 요청에 대한 정확한 View를 지정하지 않으면, 협업 시 다른 인원이 쉽게 파악하지 못할 수 있음.
	 * 단순히 생략가능하다는 편리함에만 생각하면 안됨.
	 */
	
	public static final Logger log = LoggerFactory.getLogger(AdminLoginController.class);
	
	@Autowired
	private AdminLoginUseCase adminLoginUseCase;
	
	// 관리자 로그인 페이지 진입
	@GetMapping
	public String adminLoginGet() {
		log.info("관리자 로그인 페이지 진입");
		
		return "/admin/login/login";
	}
	
	// 관리자 로그인
	@ResponseBody
	@PostMapping
	public String adminLoginPost(String id, String pwd, HttpSession session) {
		String admin_id = adminLoginUseCase.getAdminId(id, pwd);
		
		if (admin_id != "") {
			session.setAttribute("id", id);
		}

		return admin_id;
	}
	
}
