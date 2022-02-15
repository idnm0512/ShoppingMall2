package com.thisfit.shoppingmall.user.item.repository;

import com.thisfit.shoppingmall.user.item.domain.repository.ItemReviewGateway;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewInsertVO;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewModifyVO;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReview;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemReviewJpa implements ItemReviewGateway {

    @Autowired
    private ItemReviewJpaRepository itemReviewJpaRepository;

    // 상품 리뷰 리스트
    @Override
    public List<ItemReview> getItemReviewList(int item_no) {
        return itemReviewJpaRepository.findItemReviewList(item_no);
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
                                          .itemNo(itemReviewInsertVO.getItem_no())
                                          .userId(itemReviewInsertVO.getUser_id())
                                          .content(itemReviewInsertVO.getContent())
                                          .grade(itemReviewInsertVO.getGrade())
                                          .reviewImg(itemReviewInsertVO.getReview_img())
                                          .build();

        itemReviewJpaRepository.save(itemReview);
    }

    // 상품 리뷰 수정하기
    @Override
    @Modifying
    @Transactional
    public void modifyItemReview(ItemReviewModifyVO itemReviewModifyVO) {
        ItemReview itemReview = itemReviewJpaRepository.findByReviewNo(itemReviewModifyVO.getReview_no());

        itemReview.changeItemReview(itemReviewModifyVO.getGrade(),
                                    itemReviewModifyVO.getContent(),
                                    itemReviewModifyVO.getReview_img());

        itemReviewJpaRepository.save(itemReview);
    }

    // 상품 리뷰 삭제
    @Override
    @Modifying
    @Transactional
    public void deleteItemReview(int review_no) {
        itemReviewJpaRepository.deleteById(review_no);
    }

}
