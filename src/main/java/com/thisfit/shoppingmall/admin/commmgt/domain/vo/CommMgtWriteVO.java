package com.thisfit.shoppingmall.admin.commmgt.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class CommMgtWriteVO {
	
	private final String title;
	private final String content;
	private final String writer;
	private final String category;

}
