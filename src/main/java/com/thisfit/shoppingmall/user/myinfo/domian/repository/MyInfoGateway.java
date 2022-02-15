package com.thisfit.shoppingmall.user.myinfo.domian.repository;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import com.thisfit.shoppingmall.user.myinfo.domian.vo.MyInfoModifyVO;

public interface MyInfoGateway {

	// 내 정보 보기
	public User getMyInfo(String id);
	
	// 내 정보 수정
	public void modifyMyInfo(MyInfoModifyVO modifyMyInfoVO);
	
	// 비빌번호 변경
	public void modifyPwd(String pwd, String id);
	
	// 회원탈퇴 (상태변경)
	public void deleteUser(String id);
	
}
