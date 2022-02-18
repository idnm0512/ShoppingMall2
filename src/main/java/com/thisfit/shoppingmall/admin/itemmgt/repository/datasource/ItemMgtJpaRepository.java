package com.thisfit.shoppingmall.admin.itemmgt.repository.datasource;

import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMgtJpaRepository extends JpaRepository<Item, Integer> {

    // 상품수정을 위한 조회
    Item findByNo(int no);

    // 마지막(방금 등록된) 상품 번호 가져오기 (옵션등록을 위함)
    // 과연 Jpa 메서드로 바꾸는게 효율적일까?
    @Query(value = "select no from item_tb " +
                   "order by no desc " +
                   "limit 1",
           nativeQuery = true)
    int findItemNo();

}
