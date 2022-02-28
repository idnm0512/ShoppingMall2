package com.thisfit.shoppingmall.admin.qnamgt.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.admin.qnamgt.domain.dto.QNAMgtAnswerRequest;
import com.thisfit.shoppingmall.admin.qnamgt.domain.repository.QNAMgtAnswerGateway;
import com.thisfit.shoppingmall.admin.qnamgt.domain.repository.QNAMgtGateway;
import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerDetailVO;
import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerModifyVO;
import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerWriteVO;
import com.thisfit.shoppingmall.admin.qnamgt.repository.datasource.QNAMgtAnswer;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNARequest;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNANoAnswerVO;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNAWithAnswerVO;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import com.thisfit.shoppingmall.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QNAMgtUseCase {

	private final QNAMgtGateway qnaMgtGateway;
	private final QNAMgtAnswerGateway qnaMgtAnswerGateway;
	
	// 문의관리(미답변)
	public List<QNANoAnswerVO> getQnaListNoAnswer(boolean state, Pageable pageable) {
		Page<QNA> qnaList = qnaMgtGateway.getQnaListNoAnswer(state,
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

	// 문의관리(답변완료) + 답변 리스트
	public List<QNAWithAnswerVO> getQnaListWithAnswer(Pageable pageable) {
		List<QNAWithAnswer> qnaWithAnswerList = qnaMgtGateway.getQnaListWithAnswer(pageable);

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
	
	// 답변 디테일
	public QNAMgtAnswerDetailVO getQnaMgtAnswerDetail(int re_no) {
		QNAMgtAnswer qnaMgtAnswer = qnaMgtAnswerGateway.getQnaMgtAnswerDetail(re_no);
		
		QNAMgtAnswerDetailVO qnaMgtAnswerDetailVO =
										new QNAMgtAnswerDetailVO(qnaMgtAnswer.getReNo(),
																 qnaMgtAnswer.getReTitle(),
																 qnaMgtAnswer.getReContent(),
																 qnaMgtAnswer.getReWriter(),
																 qnaMgtAnswer.getReUpdateDate());
		
		return qnaMgtAnswerDetailVO;
	}
	
	// 답변 작성
	public void writeQnaMgtAnswer(QNARequest qnaRequest) {
		QNAMgtAnswerWriteVO qnaMgtAnswerWriteVO = new QNAMgtAnswerWriteVO(qnaRequest.getNo(),
																		  qnaRequest.getTitle(),
																		  qnaRequest.getContent(),
																		  qnaRequest.getWriter());

		qnaMgtAnswerGateway.writeQnaMgtAnswer(qnaMgtAnswerWriteVO);

		qnaMgtGateway.modifyQnaMgtState(qnaRequest.getNo());
	}
	
	// 답변 수정
	public void modifyQnaMgtAnswer(QNAMgtAnswerRequest qnaMgtAnswerRequest) {
		QNAMgtAnswerModifyVO qnaMgtAnswerModifyVO =
										new QNAMgtAnswerModifyVO(qnaMgtAnswerRequest.getRe_no(),
																 qnaMgtAnswerRequest.getRe_content());

		qnaMgtAnswerGateway.modifyQnaMgtAnswer(qnaMgtAnswerModifyVO);
	}
	
	// 문의관리 페이징 처리
	public PageMaker pagingQnaMgt(boolean state, Pageable pageable) {
		int total = qnaMgtGateway.getTotalQna(state);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
