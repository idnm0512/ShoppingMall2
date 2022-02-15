package com.thisfit.shoppingmall.user.join.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class JoinVO {

	private final String id;
	private final String pwd;
	private final String name;
	private final String address;
	private final String address2;
	private final String phone;
	private final String phone2;
	private final String email;
	private final String birth;

}
