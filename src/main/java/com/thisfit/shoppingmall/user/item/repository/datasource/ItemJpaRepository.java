package com.thisfit.shoppingmall.user.item.repository.datasource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Integer> {

    // 상품 리스트
    Page<Item> findByCategoryContainsAndCategory2Contains(String category, String category2, Pageable pageable);

    // 상품 디테일
    Item findByNo(int no);

    // 전체 상품 수
    int countByCategoryContainsAndCategory2Contains(String category, String category2);

    // 가장 최근 데이터 1개중 no column만 (test에 쓰임)
    // 메서드명으로 쿼리 생성시 findFirstByOrderByNoDesc();
    // 하지만, 모든 row를 가져오기 때문에 @Query 사용
    @Query(value = "select no from ITEM_TB " +
                    "order by no desc " +
                    "limit 1",
           nativeQuery = true)
    int findLastNo();

}
