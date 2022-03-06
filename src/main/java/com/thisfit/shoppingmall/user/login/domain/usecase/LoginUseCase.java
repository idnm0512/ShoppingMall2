package com.thisfit.shoppingmall.user.login.domain.usecase;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.thisfit.shoppingmall.user.login.domain.dto.LoginRequest;
import com.thisfit.shoppingmall.user.login.domain.repository.LoginGateway;
import com.thisfit.shoppingmall.user.myinfo.domian.repository.MyInfoGateway;
import com.thisfit.shoppingmall.util.kakaologin.KakaoAccessToken;
import com.thisfit.shoppingmall.util.kakaologin.KakaoUniqueNo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUseCase {

	private final LoginGateway loginGateway;
	private final MyInfoGateway myInfoGateway;
	private final KakaoAccessToken kakaoToken;
	private final KakaoUniqueNo kakaoNo;
	
	// 로그인
	public String getUserId(LoginRequest loginRequest) {
		return loginGateway.login(loginRequest);
	}
	
	// 카카오 로그인
	public void kakaoLogin(String code, HttpSession session) {
		
		String accessToken = kakaoToken.getAccessToken(code);
		
		HashMap<String, Object> userInfo = kakaoNo.getUserInfo(accessToken);
		
		if (userInfo.get("email") != null) {
			session.setAttribute("id", userInfo.get("id"));
			session.setAttribute("email", userInfo.get("email"));
			session.setAttribute("name", userInfo.get("nickname"));
			session.setAttribute("accessToken", accessToken);
		}
	}
	
	// 아이디 찾기
	public String findId(String name, String email) {
		return loginGateway.findId(name, email);
	}
	
	// 비밀번호 찾기
	public String findPwd(String id, String email) {
		String pwd = loginGateway.findPwd(id, email);

		if (pwd != null) {
			String tempPwd = RandomStringUtils.randomAlphanumeric(10);

			myInfoGateway.modifyPwd(tempPwd, id);

			return tempPwd;
		} else {
			return null;
		}
	}
	
}
