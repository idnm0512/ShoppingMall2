package com.thisfit.shoppingmall.admin.qnamgt.repository;

import com.thisfit.shoppingmall.admin.qnamgt.domain.repository.QNAMgtAnswerGateway;
import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerModifyVO;
import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerWriteVO;
import com.thisfit.shoppingmall.admin.qnamgt.repository.datasource.QNAMgtAnswer;
import com.thisfit.shoppingmall.admin.qnamgt.repository.datasource.QNAMgtAnswerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QNAMgtAnswerJpa implements QNAMgtAnswerGateway {

    @Autowired
    private QNAMgtAnswerJpaRepository qnaMgtAnswerJpaRepository;

    // 답변 디테일
    @Override
    public QNAMgtAnswer getQnaMgtAnswerDetail(int re_no) {
        return qnaMgtAnswerJpaRepository.findByReNo(re_no);
    }

    // 답변작성
    @Override
    @Modifying
    @Transactional
    public void writeQnaMgtAnswer(QNAMgtAnswerWriteVO qnaMgtAnswerWriteVO) {
        QNAMgtAnswer qnaMgtAnswer = QNAMgtAnswer.builder()
                                                .qno(qnaMgtAnswerWriteVO.getQno())
                                                .reTitle(qnaMgtAnswerWriteVO.getRe_title())
                                                .reContent(qnaMgtAnswerWriteVO.getRe_content())
                                                .reWriter(qnaMgtAnswerWriteVO.getRe_writer())
                                                .build();

        qnaMgtAnswerJpaRepository.save(qnaMgtAnswer);
    }

    // 답변수정
    @Override
    @Modifying
    @Transactional
    public void modifyQnaMgtAnswer(QNAMgtAnswerModifyVO qnaMgtAnswerModifyVO) {
        QNAMgtAnswer qnaMgtAnswer = qnaMgtAnswerJpaRepository.findByReNo(qnaMgtAnswerModifyVO.getRe_no());

        qnaMgtAnswer.changeReContent(qnaMgtAnswerModifyVO.getRe_content());

        qnaMgtAnswerJpaRepository.save(qnaMgtAnswer);
    }

}
