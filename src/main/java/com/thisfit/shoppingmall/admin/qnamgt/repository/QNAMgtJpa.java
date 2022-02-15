package com.thisfit.shoppingmall.admin.qnamgt.repository;

import java.util.List;

import com.thisfit.shoppingmall.admin.qnamgt.domain.repository.QNAMgtGateway;
import com.thisfit.shoppingmall.admin.qnamgt.repository.datasource.QNAMgtJpaRepository;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QNAMgtJpa implements QNAMgtGateway {

	@Autowired
	private QNAMgtJpaRepository qnaMgtJpaRepository;
	
	// 문의관리(미답변)
	@Override
	public Page<QNA> getQnaListNoAnswer(boolean state, Pageable pageable) {
		return qnaMgtJpaRepository.findByState(state, pageable);
	}

	// 문의관리(답변완료) + 답변 리스트
	@Override
	public List<QNAWithAnswer> getQnaListWithAnswer(Pageable pageable) {
		return qnaMgtJpaRepository.findQnaMgtList(pageable);
	}

	// 답변작성시 원글 state 변경
	@Override
	@Modifying
	@Transactional
	public void modifyQnaMgtState(int no) {
		QNA qna = qnaMgtJpaRepository.findByNo(no);

		qna.changeState(true);

		qnaMgtJpaRepository.save(qna);
	}

	// 전체 문의 수
	@Override
	public int getTotalQna(boolean state) {
		return qnaMgtJpaRepository.countByState(state);
	}

}
