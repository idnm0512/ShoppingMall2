package com.thisfit.shoppingmall.admin.qnamgt.domain.repository;

import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerModifyVO;
import com.thisfit.shoppingmall.admin.qnamgt.domain.vo.QNAMgtAnswerWriteVO;
import com.thisfit.shoppingmall.admin.qnamgt.repository.datasource.QNAMgtAnswer;

public interface QNAMgtAnswerGateway {

    // 답변 디테일
    public QNAMgtAnswer getQnaMgtAnswerDetail(int re_no);

    // 답변작성
    public void writeQnaMgtAnswer(QNAMgtAnswerWriteVO qnaMgtAnswerVO);

    // 답변수정
    public void modifyQnaMgtAnswer(QNAMgtAnswerModifyVO qnaMgtAnswerModifyVO);

}
