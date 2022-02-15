package com.thisfit.shoppingmall.admin.login.repository;

import com.thisfit.shoppingmall.admin.login.domain.repository.AdminLoginGateway;
import com.thisfit.shoppingmall.admin.login.domain.vo.AdminLoginVO;
import com.thisfit.shoppingmall.admin.login.repository.datasource.AdminLogin;
import com.thisfit.shoppingmall.admin.login.repository.datasource.AdminLoginJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginJpa implements AdminLoginGateway {
	
	@Autowired
	private AdminLoginJpaRepository adminLoginJpaRepository;
	
	// 관리자 로그인
	@Override
	public String adminLogin(AdminLoginVO adminLoginVO) {
		AdminLogin adminLogin = AdminLogin.builder()
										  .id(adminLoginVO.getId())
										  .pwd(adminLoginVO.getPwd())
										  .build();

		return adminLoginJpaRepository.findAdminId(adminLogin);
	}
	
}
