package com.thisfit.shoppingmall.admin.qnamgt.repository.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QNAMgtAnswerJpaRepository extends JpaRepository<QNAMgtAnswer, Integer> {

    // 답변 디테일 & 답변수정을 위한 조회
    QNAMgtAnswer findByReNo(int re_no);

}
