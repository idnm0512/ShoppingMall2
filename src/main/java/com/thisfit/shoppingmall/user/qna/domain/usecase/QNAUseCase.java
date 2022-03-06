package com.thisfit.shoppingmall.user.qna.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.user.qna.domain.dto.QNARequest;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.domain.repository.QNAGateway;
import com.thisfit.shoppingmall.user.qna.domain.vo.*;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import com.thisfit.shoppingmall.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QNAUseCase {

	private final QNAGateway qnaGateway;
	
	// 1:1문의(미답변)
	public List<QNANoAnswerVO> getQnaListNoAnswer(QNARequest qnaRequest, Pageable pageable) {
		Page<QNA> qnaList = qnaGateway.getQnaListNoAnswer(qnaRequest.getWriter(),
														  qnaRequest.isState(),
														  pageable);

		List<QNANoAnswerVO> qnaNoAnswerVOList = new ArrayList<>();

		for (int i = 0; i < qnaList.getContent().size(); i++) {
			QNA qna = qnaList.getContent().get(i);

			QNANoAnswerVO qnaNoAnswerVO = new QNANoAnswerVO(qna.getNo(),
															qna.getTitle(),
															qna.getWriter(),
															qna.isState(),
															qna.getUpdateDate());

			qnaNoAnswerVOList.add(qnaNoAnswerVO);
		}

		return qnaNoAnswerVOList;
	}

	// 1:1문의(답변완료) + 답변 리스트
	public List<QNAWithAnswerVO> getQnaListWithAnswer(QNARequest qnaRequest, Pageable pageable) {
		List<QNAWithAnswer> qnaWithAnswerList = qnaGateway.getQnaListWithAnswer(qnaRequest.getWriter(),
																				pageable);

		List<QNAWithAnswerVO> qnaWithAnswerVOList = new ArrayList<>();

		for (int i = 0; i < qnaWithAnswerList.size(); i++) {
			QNAWithAnswer qnaWithAnswer = qnaWithAnswerList.get(i);

			QNAWithAnswerVO qnaWithAnswerVO = new QNAWithAnswerVO(qnaWithAnswer.getNo(),
																  qnaWithAnswer.getRe_no(),
																  qnaWithAnswer.getTitle(),
																  qnaWithAnswer.getWriter(),
															  	  qnaWithAnswer.getRe_title(),
																  qnaWithAnswer.getRe_writer(),
																  qnaWithAnswer.isState(),
																  qnaWithAnswer.getUpdate_date(),
																  qnaWithAnswer.getRe_update_date());

			qnaWithAnswerVOList.add(qnaWithAnswerVO);
		}

		return qnaWithAnswerVOList;
	}

	// 1:1문의 디테일
	public QNADetailVO getQnaDetail(int no) {
		QNA qna =  qnaGateway.getQnaDetail(no);
		
		QNADetailVO qnaDetailVO = new QNADetailVO(qna.getNo(),
												  qna.getTitle(),
												  qna.getContent(),
												  qna.getWriter(),
												  qna.isState(),
												  qna.getUpdateDate());
		
		return qnaDetailVO;
	}
	
	// 1:1문의 작성
	public void writeQna(QNARequest qnaRequest) {
		QNAWriteVO qnaWriteVO = new QNAWriteVO(qnaRequest.getTitle(),
											   qnaRequest.getContent(),
											   qnaRequest.getWriter());

		qnaGateway.writeQna(qnaWriteVO);
	}
	
	// 1:1문의 수정
	public void modifyQna(QNARequest qnaRequest) {
		QNAModifyVO qnaModifyVO = new QNAModifyVO(qnaRequest.getNo(),
												  qnaRequest.getTitle(),
												  qnaRequest.getContent());

		qnaGateway.modifyQna(qnaModifyVO);
	}
	
	// 1:1문의 삭제
	public void deleteQna(int no) {
		qnaGateway.deleteQna(no);
	}
	
	// 1:1문의 페이징 처리
	public PageMaker pagingQna(QNARequest qnaRequest, Pageable pageable) {
		int total = qnaGateway.getTotalQna(qnaRequest.getWriter(), qnaRequest.isState());

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
