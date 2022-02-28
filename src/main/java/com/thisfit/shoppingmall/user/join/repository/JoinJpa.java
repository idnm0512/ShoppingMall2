package com.thisfit.shoppingmall.user.join.repository;

import com.thisfit.shoppingmall.user.join.domain.repository.JoinGateway;
import com.thisfit.shoppingmall.user.join.domain.vo.JoinVO;
import com.thisfit.shoppingmall.user.join.repository.datasource.JoinJpaRepository;
import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinJpa implements JoinGateway {

	private final JoinJpaRepository joinJpaRepository;

	// 회원가입
	@Override
	@Modifying
	@Transactional
	public void join(JoinVO joinVO) {
		User user = User.builder()
						.id(joinVO.getId())
						.pwd(joinVO.getPwd())
						.name(joinVO.getName())
						.address(joinVO.getAddress())
						.address2(joinVO.getAddress2())
						.phone(joinVO.getPhone())
						.phone2(joinVO.getPhone2())
						.email(joinVO.getEmail())
						.birth(joinVO.getBirth())
						.state(true)
						.build();

		joinJpaRepository.save(user);
	}
	
	// 아이디 중복확인
	@Override
	public String idCheck(String id) {
		return joinJpaRepository.findId(id);
	}
	
}
