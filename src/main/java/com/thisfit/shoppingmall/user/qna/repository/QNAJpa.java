package com.thisfit.shoppingmall.user.qna.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.domain.repository.QNAGateway;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNAModifyVO;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNAWriteVO;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNAJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QNAJpa implements QNAGateway {

	@Autowired
	private QNAJpaRepository qnaJpaRepository;
	
	// 1:1문의 (미답변)
	@Override
	public Page<QNA> getQnaListNoAnswer(String writer, boolean state, Pageable pageable) {
		return qnaJpaRepository.findByWriterAndState(writer, state, pageable);
	}

	// 1:1문의, 답변 리스트 (답변)
	@Override
	public List<QNAWithAnswer> getQnaListWithAnswer(String writer, Pageable pageable) {
		return qnaJpaRepository.findByWriter(writer, pageable);
	}

	// 1:1문의 상세보기
	@Override
	public QNA getQnaDetail(int no) {
		return qnaJpaRepository.findByNo(no);
	}
	
	// 전체 게시물 수
	@Override
	public int getTotalQna(String writer, boolean state) {
		return qnaJpaRepository.countByWriterAndState(writer, state);
	}
	
	// 1:1문의 작성
	@Override
	@Modifying
	@Transactional
	public void writeQna(QNAWriteVO qnaWriteVO) {
		QNA qna = QNA.builder()
					 .title(qnaWriteVO.getTitle())
					 .content(qnaWriteVO.getContent())
					 .writer(qnaWriteVO.getWriter())
					 .build();

		qnaJpaRepository.save(qna);
	}
	
	// 1:1문의 수정
	@Override
	@Modifying
	@Transactional
	public void modifyQna(QNAModifyVO qnaModifyVO) {
		QNA qna = qnaJpaRepository.findByNo(qnaModifyVO.getNo());

		qna.changeQNA(qnaModifyVO.getTitle(),
					  qnaModifyVO.getContent());

		qnaJpaRepository.save(qna);
	}
	
	// 1:1문의 삭제
	@Override
	@Modifying
	@Transactional
	public void deleteQna(int no) {
		qnaJpaRepository.deleteById(no);
	}
	
}
