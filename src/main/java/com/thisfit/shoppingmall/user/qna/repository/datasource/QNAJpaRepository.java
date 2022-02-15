package com.thisfit.shoppingmall.user.qna.repository.datasource;

import com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QNAJpaRepository extends JpaRepository<QNA, Integer> {

    // 1:1문의 (미답변)
    Page<QNA> findByWriterAndState(String writer, boolean state, Pageable pageable);

    // 1:1문의, 답변 리스트 (답변)
    @Query("select new com.thisfit.shoppingmall.user.qna.domain.dto.QNAWithAnswer(" +
                                                "q.no, q.title, q.writer, q.state, q.updateDate, " +
                                                "qma.reNo, qma.reTitle, qma.reWriter, qma.reUpdateDate) " +
             "from QNA q " +
             "join QNAMgtAnswer qma on q.no = qma.qno and q.writer = :writer ")
    List<QNAWithAnswer> findByWriter(String writer, Pageable pageable);

    // 1:1문의 상세보기 & 1:1문의 수정
    QNA findByNo(int no);

    // 전체 게시물 수
    int countByWriterAndState(String writer, boolean state);

}
