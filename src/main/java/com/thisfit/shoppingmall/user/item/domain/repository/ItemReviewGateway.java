package com.thisfit.shoppingmall.user.item.domain.repository;

import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewInsertVO;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewModifyVO;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReview;

import java.util.List;

public interface ItemReviewGateway {

    // 상품 리뷰 리스트
    public List<ItemReview> getItemReviewList(int itemNo);

    // 상품 리뷰 디테일
    public ItemReview getItemReviewDetail(int reviewNo);

    // 상품 리뷰 등록하기
    public void insertItemReview(ItemReviewInsertVO itemReviewInsertVO);

    // 상품 리뷰 수정하기
    public void modifyItemReview(ItemReviewModifyVO itemReviewModifyVO);

    // 상품 리뷰 삭제
    public void deleteItemReview(int reviewNo);

}
