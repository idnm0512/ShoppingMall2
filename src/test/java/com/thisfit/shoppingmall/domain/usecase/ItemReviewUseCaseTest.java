package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.item.domain.dto.ItemReviewRequest;
import com.thisfit.shoppingmall.user.item.domain.usecase.ItemReviewUseCase;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewInfoVO;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReview;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReviewJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemReviewUseCaseTest {

    @Autowired
    private ItemReviewUseCase itemReviewUseCase;

    @Autowired
    private ItemReviewJpaRepository itemReviewJpaRepository;

    private int itemNo = 1;
    private int grade = 5;

    private String userId = "id123";
    private String content = "content";
    private String reviewImg = "reviewImg";

    private byte[] byteArr = new String("test").getBytes();

    private MockMultipartFile reviewImgFile =
                                new MockMultipartFile("name", "test", "txt", byteArr);

    @Before
    public void saveItemReview() {
        itemReviewJpaRepository.save(ItemReview.builder()
                                               .itemNo(itemNo)
                                               .userId(userId)
                                               .content(content)
                                               .grade(grade)
                                               .reviewImg(reviewImg)
                                               .build());
    }

    @After
    public void cleanup() {
        itemReviewJpaRepository.deleteAll();
    }

    // 상품 리뷰 리스트
    @Test
    public void getItemReviewListTest() {
        // given + when
        List<ItemReviewInfoVO> itemReviewInfoVOList = itemReviewUseCase.getItemReviewList(itemNo);

        // then
        ItemReviewInfoVO itemReviewInfoVO = itemReviewInfoVOList.get(0);

        assertThat(itemReviewInfoVO.getItemNo()).isEqualTo(itemNo);
        assertThat(itemReviewInfoVO.getGrade()).isEqualTo(grade);
        assertThat(itemReviewInfoVO.getUserId()).isEqualTo(userId);
        assertThat(itemReviewInfoVO.getContent()).isEqualTo(content);
        assertThat(itemReviewInfoVO.getReviewImg()).isEqualTo(reviewImg);
    }

    // 상품 리뷰 디테일
    @Test
    public void getItemReviewDetailTest() {
        // given
        Map<String, Object> map = itemReviewJpaRepository.findLastReviewNoAndReviewImg();

        int reviewNo = (int)map.get("reviewNo");

        // when
        ItemReviewInfoVO itemReviewInfoVO = itemReviewUseCase.getItemReviewDetail(reviewNo);

        // then
        assertThat(itemReviewInfoVO.getItemNo()).isEqualTo(itemNo);
        assertThat(itemReviewInfoVO.getGrade()).isEqualTo(grade);
        assertThat(itemReviewInfoVO.getUserId()).isEqualTo(userId);
        assertThat(itemReviewInfoVO.getContent()).isEqualTo(content);
        assertThat(itemReviewInfoVO.getReviewImg()).isEqualTo(reviewImg);
    }

    // 상품 리뷰 등록하기
    @Test
    public void insertItemReviewTest() {
        // given
        ItemReviewRequest itemReviewRequest = ItemReviewRequest.builder()
                                                               .itemNo(itemNo)
                                                               .grade(grade)
                                                               .userId(userId)
                                                               .content(content)
                                                               .reviewImg(reviewImg)
                                                               .reviewImgFile(reviewImgFile)
                                                               .build();

        // when
        try {
            itemReviewUseCase.insertItemReview(itemReviewRequest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: IllegalStateException 발생 (상품 리뷰 등록하기)");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: IOException 발생 (상품 리뷰 등록하기)");
        }

        // then
        Map<String, Object> map = itemReviewJpaRepository.findLastReviewNoAndReviewImg();

        reviewImg = (String)map.get("reviewImg");

        List<ItemReview> itemReviewList = itemReviewJpaRepository.findAll();

        ItemReview itemReview = itemReviewList.get(1);

        assertThat(itemReview.getItemNo()).isEqualTo(itemNo);
        assertThat(itemReview.getGrade()).isEqualTo(grade);
        assertThat(itemReview.getUserId()).isEqualTo(userId);
        assertThat(itemReview.getContent()).isEqualTo(content);
        assertThat(itemReview.getReviewImg()).isEqualTo(reviewImg);
    }

    // 상품 리뷰 수정하기
    @Test
    public void modifyItemReviewTest() {
        // given
        int reviewNo = (int)itemReviewJpaRepository.findLastReviewNoAndReviewImg().get("reviewNo");

        grade = 1;
        content = "content123";

        ItemReviewRequest itemReviewRequest = ItemReviewRequest.builder()
                                                               .reviewNo(reviewNo)
                                                               .grade(grade)
                                                               .content(content)
                                                               .reviewImg(reviewImg)
                                                               .reviewImgFile(reviewImgFile)
                                                               .build();

        // when
        try {
            itemReviewUseCase.modifyItemReview(itemReviewRequest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: IllegalStateException 발생 (상품 리뷰 수정하기)");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: IOException 발생 (상품 리뷰 수정하기)");
        }

        // then
        reviewImg = (String)itemReviewJpaRepository.findLastReviewNoAndReviewImg().get("reviewImg");

        List<ItemReview> itemReviewList = itemReviewJpaRepository.findAll();

        ItemReview itemReview = itemReviewList.get(0);

        assertThat(itemReview.getGrade()).isEqualTo(grade);
        assertThat(itemReview.getContent()).isEqualTo(content);
        assertThat(itemReview.getReviewImg()).isEqualTo(reviewImg);
    }

    // 상품 리뷰 삭제하기
    @Test
    public void deleteItemReviewTest() {
        // given
        Map<String, Object> map = itemReviewJpaRepository.findLastReviewNoAndReviewImg();

        int reviewNo = (int)map.get("reviewNo");

        // when
        itemReviewUseCase.deleteItemReview(reviewNo, null);

        // then
        List<ItemReview> itemReviewList = itemReviewJpaRepository.findAll();

        assertThat(itemReviewList).isEmpty();
    }

}
