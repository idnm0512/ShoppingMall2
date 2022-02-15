package com.thisfit.shoppingmall.admin.qnamgt.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class QNAMgtAnswerWriteVO {
	
	private final int qno;

	private final String re_title;
	private final String re_content;
	private final String re_writer;

}
