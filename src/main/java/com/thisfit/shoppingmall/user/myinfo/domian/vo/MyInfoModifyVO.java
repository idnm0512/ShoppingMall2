package com.thisfit.shoppingmall.user.myinfo.domian.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class MyInfoModifyVO {
	
	private final String id;
	private final String address;
	private final String address2;
	private final String phone;
	private final String phone2;
	private final String email;
	private final String birth;

}
