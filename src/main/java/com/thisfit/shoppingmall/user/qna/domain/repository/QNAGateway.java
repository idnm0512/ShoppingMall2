package com.thisfit.shoppingmall.user.qna.domain.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNAModifyVO;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNAWriteVO;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QNAGateway {

	// 1:1문의 (미답변)
	public Page<QNA> getQnaListNoAnswer(String writer, boolean state, Pageable pageable);

	// 1:1문의, 답변 리스트 (답변)
	public List<QNAWithAnswer> getQnaListWithAnswer(String writer, Pageable pageable);

	// 1:1문의 상세보기
	public QNA getQnaDetail(int no);
	
	// 전체 게시물 수
	public int getTotalQna(String writer, boolean state);
	
	// 1:1문의 작성
	public void writeQna(QNAWriteVO qnaWriteVO);
	
	// 1:1문의 수정
	public void modifyQna(QNAModifyVO qnaModifyVO);
	
	// 1:1문의 삭제
	public void deleteQna(int no);
	
}
