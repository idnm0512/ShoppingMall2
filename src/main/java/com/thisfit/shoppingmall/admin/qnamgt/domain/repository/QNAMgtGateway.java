package com.thisfit.shoppingmall.admin.qnamgt.domain.repository;

import java.util.List;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QNAMgtGateway {

	// 문의관리(미답변)
	public Page<QNA> getQnaListNoAnswer(boolean state, Pageable pageable);

	// 문의관리(답변완료) + 답변 리스트
	public List<QNAWithAnswer> getQnaListWithAnswer(Pageable pageable);

	// 답변작성시 원글 state 변경
	public void modifyQnaMgtState(int no);
	
	// 전체 문의 수
	public int getTotalQna(boolean state);
	
}
