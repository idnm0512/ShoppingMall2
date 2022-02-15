package com.thisfit.shoppingmall.admin.login.domain.usecase;

import com.thisfit.shoppingmall.admin.login.domain.repository.AdminLoginGateway;
import com.thisfit.shoppingmall.admin.login.domain.vo.AdminLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminLoginUseCase {
	
	private final AdminLoginGateway adminLoginGateway;

	// 관리자 로그인
	public String getAdminId(String id, String pwd) {
		AdminLoginVO adminLoginVO = new AdminLoginVO(id, pwd);
		
		return adminLoginGateway.adminLogin(adminLoginVO);
	}
	
}
