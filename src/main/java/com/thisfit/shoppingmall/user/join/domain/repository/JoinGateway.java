package com.thisfit.shoppingmall.user.join.domain.repository;

import com.thisfit.shoppingmall.user.join.domain.vo.JoinVO;

public interface JoinGateway {

	// 회원가입
	public void join(JoinVO joinVO);
	
	// 아이디 중복확인
	public String idCheck(String id);
	
}
