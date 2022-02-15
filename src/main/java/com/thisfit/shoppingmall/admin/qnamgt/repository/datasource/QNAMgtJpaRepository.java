package com.thisfit.shoppingmall.admin.qnamgt.repository.datasource;

import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QNAMgtJpaRepository extends JpaRepository<QNA, Integer> {

    // 문의관리(미답변)
    Page<QNA> findByState(boolean state, Pageable pageable);

    // 문의관리(답변완료) + 답변 리스트
    @Query("select new com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer(" +
                      "q.no, q.title, q.writer, q.state, q.updateDate, " +
                      "qma.reNo, qma.reTitle, qma.reWriter, qma.reUpdateDate) " +
             "from QNA q " +
             "join QNAMgtAnswer qma on q.no = qma.qno ")
    List<QNAWithAnswer> findQnaMgtList(Pageable pageable);

    // 답변작성시 원글 state 변경을 위한 조회
    QNA findByNo(int no);

    // 전체 문의 수
    int countByState(boolean state);

}
