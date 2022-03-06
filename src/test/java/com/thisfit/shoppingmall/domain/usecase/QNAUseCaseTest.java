package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.qna.domain.dto.QNARequest;
import com.thisfit.shoppingmall.user.qna.domain.usecase.QNAUseCase;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNADetailVO;
import com.thisfit.shoppingmall.user.qna.domain.vo.QNANoAnswerVO;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNA;
import com.thisfit.shoppingmall.user.qna.repository.datasource.QNAJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QNAUseCaseTest {

    @Autowired
    private QNAUseCase qnaUseCase;

    @Autowired
    private QNAJpaRepository qnaJpaRepository;

    private String title = "title";
    private String content = "content";
    private String writer = "writer";

    private boolean state = false;

    @Before
    public void saveQNA() {
        qnaJpaRepository.save(QNA.builder()
                                 .title(title)
                                 .content(content)
                                 .writer(writer)
                                 .build());
    }

    @After
    public void cleanup() {
        qnaJpaRepository.deleteAll();
    }

    // 1:1문의(미답변)
    @Test
    public void getQnaListNoAnswerTest() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        QNARequest qnaRequest = QNARequest.builder()
                                          .writer(writer)
                                          .state(state)
                                          .build();

        // when
        List<QNANoAnswerVO> qnaNoAnswerVOList = qnaUseCase.getQnaListNoAnswer(qnaRequest, pageable);

        // then
        QNANoAnswerVO qnaNoAnswerVO = qnaNoAnswerVOList.get(0);

        assertThat(qnaNoAnswerVO.getTitle()).isEqualTo(title);
        assertThat(qnaNoAnswerVO.getWriter()).isEqualTo(writer);
        assertThat(qnaNoAnswerVO.isState()).isEqualTo(state);
    }

    // 1:1문의 디테일
    @Test
    public void getQnaDetailTest() {
        // given
        int no = qnaJpaRepository.findLastNo();

        System.out.println("@@@@@@@@@@@@@@@@@@@@ "+no);

        // when
        QNADetailVO qnaDetailVO = qnaUseCase.getQnaDetail(no);

        // then
        assertThat(qnaDetailVO.getTitle()).isEqualTo(title);
        assertThat(qnaDetailVO.getContent()).isEqualTo(content);
        assertThat(qnaDetailVO.getWriter()).isEqualTo(writer);
        assertThat(qnaDetailVO.isState()).isEqualTo(state);
    }
    
    // 1:1문의 작성
    @Test
    public void writeQna() {
        // given
        QNARequest qnaRequest = QNARequest.builder()
                                          .title(title)
                                          .content(content)
                                          .writer(writer)
                                          .build();

        // when
        qnaUseCase.writeQna(qnaRequest);

        // then
        List<QNA> qnaList = qnaJpaRepository.findAll();

        QNA qna = qnaList.get(1);

        assertThat(qna.getTitle()).isEqualTo(title);
        assertThat(qna.getContent()).isEqualTo(content);
        assertThat(qna.getWriter()).isEqualTo(writer);
    }

    // 1:1문의 수정
    @Test
    public void modifyQnaTest() {
        // given
        int no = qnaJpaRepository.findLastNo();

        title = "modifyTitle";
        content = "modifyContent";

        QNARequest qnaRequest = QNARequest.builder()
                                          .no(no)
                                          .title(title)
                                          .content(content)
                                          .build();

        // when
        qnaUseCase.modifyQna(qnaRequest);

        // then
        List<QNA> qnaList = qnaJpaRepository.findAll();

        QNA qna = qnaList.get(0);

        assertThat(qna.getTitle()).isEqualTo(title);
        assertThat(qna.getContent()).isEqualTo(content);
    }

    // 1:1문의 삭제
    @Test
    public void deleteQnaTest() {
        // given
        int no = qnaJpaRepository.findLastNo();

        // when
        qnaUseCase.deleteQna(no);

        // then
        List<QNA> qnaList = qnaJpaRepository.findAll();

        assertThat(qnaList).isEmpty();
    }

}
