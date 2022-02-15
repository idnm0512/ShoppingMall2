package com.thisfit.shoppingmall.user.qna.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class QNAWriteVO {

	private final String title;
	private final String content;
	private final String writer;

}
