package com.thisfit.shoppingmall.user.login.domain.repository;

import com.thisfit.shoppingmall.user.login.domain.dto.LoginRequest;

public interface LoginGateway {
	
	// 로그인 (아이디, 패스워드 체크)
	public String login(LoginRequest loginRequest);
	
	// 아이디 찾기 (이름, 이메일)
	public String findId(String name, String email);
	
	// 비밀번호 찾기 (아이디, 이메일)
	public String findPwd(String id, String email);
	
}
