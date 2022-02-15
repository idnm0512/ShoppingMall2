package com.thisfit.shoppingmall.admin.login.domain.repository;

import com.thisfit.shoppingmall.admin.login.domain.vo.AdminLoginVO;

public interface AdminLoginGateway {
	
	// 관리자 로그인
	public String adminLogin(AdminLoginVO adminLoginVO);
	
}
