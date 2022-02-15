package com.thisfit.shoppingmall.user.login.repository;

import com.thisfit.shoppingmall.user.login.domain.dto.LoginRequest;
import com.thisfit.shoppingmall.user.login.domain.repository.LoginGateway;
import com.thisfit.shoppingmall.user.login.repository.datasource.LoginJpaRepository;
import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginJpa implements LoginGateway {

	@Autowired
	private LoginJpaRepository loginJpaRepository;
	
	// 로그인 (아이디, 패스워드 체크)
	@Override
	public String login(LoginRequest loginRequest) {
		User user = User.builder()
						.id(loginRequest.getId())
						.pwd(loginRequest.getPwd())
						.build();

		return loginJpaRepository.findLoginId(user);
	}
	
	// 아이디 찾기 (이름, 이메일)
	@Override
	public String findId(String name, String email) {
		return loginJpaRepository.findId(name, email);
	}
	
	// 비밀번호 찾기 (아이디, 이메일)
	@Override
	public String findPwd(String id, String email) {
		return loginJpaRepository.findPwd(id, email);
	}
	
}
