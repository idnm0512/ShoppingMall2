package com.thisfit.shoppingmall.admin.usermgt.domain.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class UserMgtVO {

	private final int no;
	private final String id;
	private final String pwd;
	private final String name;
	private final String address;
	private final String address2;
	private final String phone;
	private final String phone2;
	private final String email;
	private final String birth;

	private final boolean state;
	
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime joindate;

	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime withdrawdate;

}
