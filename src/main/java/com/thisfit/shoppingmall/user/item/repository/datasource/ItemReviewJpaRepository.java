package com.thisfit.shoppingmall.user.item.repository.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemReviewJpaRepository extends JpaRepository<ItemReview, Integer> {

    // 상품 리뷰 리스트
    @Query("select ir from ItemReview ir " +
            "where ir.itemNo = :itemNo " +
           "order by ir.reviewNo desc")
    List<ItemReview> findItemReviewList(@Param("itemNo") int itemNo);

    // 상품 리뷰 디테일 & 상품 리뷰 수정을 위한 조회
    ItemReview findByReviewNo(int reviewNo);

    // 가장 최근 데이터 1개중 review_no, review_img column만 (test에 쓰임)
    // 메서드명으로 쿼리 생성시 findFirstByOrderByReviewNoDesc();
    // 하지만, 모든 row를 가져오기 때문에 @Query 사용
    @Query(value = "select review_no as reviewNo, review_img as reviewImg from ITEM_REVIEW_TB " +
                    "order by review_no desc " +
                    "limit 1",
           nativeQuery = true)
    Map<String, Object> findLastReviewNoAndReviewImg();

}
