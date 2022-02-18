package com.thisfit.shoppingmall.admin.itemmgt.repository;

import com.thisfit.shoppingmall.admin.itemmgt.domain.repository.ItemMgtOptGateway;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtOptInsertVO;
import com.thisfit.shoppingmall.admin.itemmgt.repository.datasource.ItemMgtOpt;
import com.thisfit.shoppingmall.admin.itemmgt.repository.datasource.ItemMgtOptJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemMgtOptJpa implements ItemMgtOptGateway {

    @Autowired
    private ItemMgtOptJpaRepository itemMgtOptJpaRepository;

    // 옵션등록
    @Override
    @Modifying
    @Transactional
    public void insertItemMgtOpt(ItemMgtOptInsertVO itemMgtOptInsertVO) {
        ItemMgtOpt itemMgtOpt = ItemMgtOpt.builder()
                                          .itemNo(itemMgtOptInsertVO.getItem_no())
                                          .qty(itemMgtOptInsertVO.getQty())
                                          .color(itemMgtOptInsertVO.getColor())
                                          .size(itemMgtOptInsertVO.getSize())
                                          .build();

        itemMgtOptJpaRepository.save(itemMgtOpt);
    }

    // 옵션 리스트 ajax
    @Override
    public List<ItemMgtOpt> getItemMgtOptList(int item_no) {
        return itemMgtOptJpaRepository.findByItemNo(item_no);
    }

    // 옵션삭제 ajax
    @Override
    @Modifying
    @Transactional
    public void deleteItemMgtOptAjax(int opt_no) {
        itemMgtOptJpaRepository.deleteById(opt_no);
    }

    // 옵션삭제 (상품삭제시 옵션도 삭제하기 위함)
    @Override
    @Modifying
    @Transactional
    public void deleteItemMgtOpt(int item_no) {
        itemMgtOptJpaRepository.deleteByItemNo(item_no);
    }

}
