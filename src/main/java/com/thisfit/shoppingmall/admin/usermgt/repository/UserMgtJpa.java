package com.thisfit.shoppingmall.admin.usermgt.repository;

import com.thisfit.shoppingmall.admin.usermgt.domain.repository.UserMgtGateway;
import com.thisfit.shoppingmall.admin.usermgt.repository.datasource.UserMgtJpaRepository;
import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserMgtJpa implements UserMgtGateway {

	private final UserMgtJpaRepository userMgtJpaRepository;
	
	// 회원 리스트
	@Override
	public Page<User> getUserMgtList(String keyword, Pageable pageable) {
		return userMgtJpaRepository.findByIdContains(keyword, pageable);
	}
	
	// 전체 회원 수
	@Override
	public int getTotalUser(String keyword) {
		return userMgtJpaRepository.countByIdContains(keyword);
	}
	
	// 회원 활성화 / 비활성화
	@Override
	@Modifying
	@Transactional
	public void modifyUserState(String id, boolean state) {
		User user = userMgtJpaRepository.findById(id);

		user.changeState(state);

		userMgtJpaRepository.save(user);
	}
	
}
