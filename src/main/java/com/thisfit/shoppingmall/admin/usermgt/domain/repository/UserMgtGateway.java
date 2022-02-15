package com.thisfit.shoppingmall.admin.usermgt.domain.repository;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserMgtGateway {

	// 회원 리스트
	public Page<User> getUserMgtList(String keyword, Pageable pageable);
	
	// 전체 회원 수
	public int getTotalUser(String keyword);
	
	// 회원 활성화 / 비활성화
	public void modifyUserState(String id, boolean state);
	
}
