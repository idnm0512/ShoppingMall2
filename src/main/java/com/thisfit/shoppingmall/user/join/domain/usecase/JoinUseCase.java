package com.thisfit.shoppingmall.user.join.domain.usecase;

import com.thisfit.shoppingmall.user.join.domain.dto.JoinRequest;
import com.thisfit.shoppingmall.user.join.domain.repository.JoinGateway;
import com.thisfit.shoppingmall.user.join.domain.vo.JoinVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinUseCase {

	private final JoinGateway joinGateway;
	
	// 회원가입
	public void join(JoinRequest joinRequest) {
		JoinVO joinVO = new JoinVO(joinRequest.getId(),
								   joinRequest.getPwd(),
								   joinRequest.getName(),
								   joinRequest.getAddress(),
								   joinRequest.getAddress2(),
								   joinRequest.getPhone(),
								   joinRequest.getPhone2(),
								   joinRequest.getEmail(),
								   joinRequest.getBirth());

		joinGateway.join(joinVO);
	}
	
	// 아이디 중복확인
	public String idCheck(String id) {
		return joinGateway.idCheck(id);
	}

}
