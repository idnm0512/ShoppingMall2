package com.thisfit.shoppingmall.admin.usermgt.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.admin.usermgt.domain.repository.UserMgtGateway;
import com.thisfit.shoppingmall.admin.usermgt.domain.vo.UserMgtVO;
import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import com.thisfit.shoppingmall.user.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMgtUseCase {

	private final UserMgtGateway userMgtGateway;

	// 회원관리 리스트
	public List<UserMgtVO> getUserMgtList(String keyword, Pageable pageable) {
		Page<User> userList = userMgtGateway.getUserMgtList(keyword,
															pageable);
		
		List<UserMgtVO> userMgtVOList = new ArrayList<>();
		
		for (int i = 0; i < userList.getContent().size(); i++) {
			User user = userList.getContent().get(i);
			
			UserMgtVO userMgtVO = new UserMgtVO(user.getNo(),
												user.getId(),
												user.getPwd(),
												user.getName(),
												user.getAddress(),
												user.getAddress2(),
												user.getPhone(),
												user.getPhone2(),
												user.getEmail(),
												user.getBirth(),
												user.isState(),
												user.getJoindate(),
												user.getWithdrawdate());
			
			userMgtVOList.add(userMgtVO);
		}
		
		return userMgtVOList;
	}
	
	// 회원 활성화 / 비활성화
	public void modifyUserState(String id, boolean state) {
		userMgtGateway.modifyUserState(id, !state);
	}
	
	// 회원관리 페이징 처리
	public PageMaker pagingUserMgt(String keyword, Pageable pageable) {
		int total = userMgtGateway.getTotalUser(keyword);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
