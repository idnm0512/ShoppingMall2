package com.thisfit.shoppingmall.user.myinfo.repository;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import com.thisfit.shoppingmall.user.myinfo.domian.repository.MyInfoGateway;
import com.thisfit.shoppingmall.user.myinfo.domian.vo.MyInfoModifyVO;
import com.thisfit.shoppingmall.user.myinfo.repository.datasource.MyInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyInfoJpa implements MyInfoGateway {

	@Autowired
	private MyInfoJpaRepository myInfoJpaRepository;
	
	// 내 정보 보기
	@Override
	public User getMyInfo(String id) {
		return myInfoJpaRepository.findById(id);
	}
	
	// 내 정보 수정
	@Override
	@Modifying
	@Transactional
	public void modifyMyInfo(MyInfoModifyVO modifyMyInfoVO) {
		User user = myInfoJpaRepository.findById(modifyMyInfoVO.getId());

		user.changeMyInfo(modifyMyInfoVO.getAddress(),
						  modifyMyInfoVO.getAddress2(),
						  modifyMyInfoVO.getPhone(),
						  modifyMyInfoVO.getPhone2(),
						  modifyMyInfoVO.getEmail(),
						  modifyMyInfoVO.getBirth());

		myInfoJpaRepository.save(user);
	}
	
	// 비빌번호 변경
	@Override
	@Modifying
	@Transactional
	public void modifyPwd(String pwd, String id) {
		User user = myInfoJpaRepository.findById(id);

		user.changePwd(pwd);

		myInfoJpaRepository.save(user);
	}
	
	// 회원탈퇴 (상태변경)
	@Override
	@Modifying
	@Transactional
	public void deleteUser(String id) {
		User user = myInfoJpaRepository.findById(id);

		user.changeState(false);

		myInfoJpaRepository.save(user);
	}
	
}
