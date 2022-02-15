package com.thisfit.shoppingmall.user.item.repository.datasource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Integer> {

    // 상품 리스트
    Page<Item> findByCategoryContainsAndCategory2Contains(String category, String category2, Pageable pageable);

    // 상품 디테일
    Item findByNo(int no);

    // 전체 상품 수
    int countByCategoryContainsAndCategory2Contains(String category, String category2);

}
