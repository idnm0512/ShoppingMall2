package com.thisfit.shoppingmall.user.item.repository;

import com.thisfit.shoppingmall.user.item.domain.repository.ItemReviewGateway;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewInsertVO;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewModifyVO;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReview;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemReviewJpa implements ItemReviewGateway {

    private final ItemReviewJpaRepository itemReviewJpaRepository;

    // 상품 리뷰 리스트
    @Override
    public List<ItemReview> getItemReviewList(int itemNo) {
        return itemReviewJpaRepository.findItemReviewList(itemNo);
    }

    // 상품 리뷰 디테일
    @Override
    public ItemReview getItemReviewDetail(int review_no) {
        return itemReviewJpaRepository.findByReviewNo(review_no);
    }

    // 상품 리뷰 등록하기
    @Override
    @Modifying
    @Transactional
    public void insertItemReview(ItemReviewInsertVO itemReviewInsertVO) {
        ItemReview itemReview = ItemReview.builder()
                                          .itemNo(itemReviewInsertVO.getItemNo())
                                          .userId(itemReviewInsertVO.getUserId())
                                          .content(itemReviewInsertVO.getContent())
                                          .grade(itemReviewInsertVO.getGrade())
                                          .reviewImg(itemReviewInsertVO.getReviewImg())
                                          .build();

        itemReviewJpaRepository.save(itemReview);
    }

    // 상품 리뷰 수정하기
    @Override
    @Modifying
    @Transactional
    public void modifyItemReview(ItemReviewModifyVO itemReviewModifyVO) {
        ItemReview itemReview = itemReviewJpaRepository.findByReviewNo(itemReviewModifyVO.getReviewNo());

        itemReview.changeItemReview(itemReviewModifyVO.getGrade(),
                                    itemReviewModifyVO.getContent(),
                                    itemReviewModifyVO.getReviewImg());

        itemReviewJpaRepository.save(itemReview);
    }

    // 상품 리뷰 삭제
    @Override
    @Modifying
    @Transactional
    public void deleteItemReview(int reviewNo) {
        itemReviewJpaRepository.deleteById(reviewNo);
    }

}
