package com.thisfit.shoppingmall.admin.itemmgt.repository.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMgtOptJpaRepository extends JpaRepository<ItemMgtOpt, Integer> {

    // 옵션 리스트 ajax
    List<ItemMgtOpt> findByItemNo(int item_no);

    // 옵션삭제 (상품삭제시 옵션도 삭제하기 위함)
    void deleteByItemNo(int item_no);

}
