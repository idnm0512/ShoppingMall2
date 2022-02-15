package com.thisfit.shoppingmall.user.myinfo.domian.usecase;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import com.thisfit.shoppingmall.user.myinfo.domian.dto.MyInfoRequest;
import com.thisfit.shoppingmall.user.myinfo.domian.repository.MyInfoGateway;
import com.thisfit.shoppingmall.user.myinfo.domian.vo.MyInfoModifyVO;
import com.thisfit.shoppingmall.user.myinfo.domian.vo.MyInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyInfoUseCase {

	private final MyInfoGateway myInfoGateway;

	// 내 정보 보기
	public MyInfoVO getMyInfo(String id) {
		User user = myInfoGateway.getMyInfo(id);

		MyInfoVO myInfoVO = new MyInfoVO(user.getId(),
										 user.getPwd(),
										 user.getName(),
										 user.getAddress(),
										 user.getAddress2(),
										 user.getPhone(),
										 user.getPhone2(),
										 user.getEmail(),
										 user.getBirth());
		
		return myInfoVO;
	}
	
	// 내 정보 수정
	public void modifyMyInfo(MyInfoRequest myInfoRequest) {
		MyInfoModifyVO myInfoModifyVO =
								new MyInfoModifyVO(myInfoRequest.getId(),
												   myInfoRequest.getAddress(),
												   myInfoRequest.getAddress2(),
												   myInfoRequest.getPhone(),
												   myInfoRequest.getPhone2(),
												   myInfoRequest.getEmail(),
												   myInfoRequest.getBirth());

		myInfoGateway.modifyMyInfo(myInfoModifyVO);
	}
	
	// 비밀번호 변경
	public void modifyPwd(String pwd, String id) {
		myInfoGateway.modifyPwd(pwd, id);
	}
	
	// 회원탈퇴 (상태변경)
	public void deleteUser(String id) {
		myInfoGateway.deleteUser(id);
	}
	
}
