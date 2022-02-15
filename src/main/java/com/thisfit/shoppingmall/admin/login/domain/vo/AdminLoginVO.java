package com.thisfit.shoppingmall.admin.login.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class AdminLoginVO {
	
	private final String id;
	private final String pwd;

}
