package com.thisfit.shoppingmall.user.item.repository.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemReviewJpaRepository extends JpaRepository<ItemReview, Integer> {

    // 상품 리뷰 리스트
    @Query("select ir from ItemReview ir " +
            "where ir.itemNo = :itemNo " +
           "order by ir.reviewNo desc")
    List<ItemReview> findItemReviewList(@Param("itemNo") int item_no);

    // 상품 리뷰 디테일 & 상품 리뷰 수정을 위한 조회
    ItemReview findByReviewNo(int review_no);

}
