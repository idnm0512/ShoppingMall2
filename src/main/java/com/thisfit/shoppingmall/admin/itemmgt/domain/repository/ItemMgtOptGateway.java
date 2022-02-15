package com.thisfit.shoppingmall.admin.itemmgt.domain.repository;

import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtOptInsertVO;
import com.thisfit.shoppingmall.admin.itemmgt.repository.datasource.ItemMgtOpt;

import java.util.List;

public interface ItemMgtOptGateway {

    // 옵션등록
    public void insertItemMgtOpt(ItemMgtOptInsertVO itemMgtOptInsertVO);

    // 옵션 리스트 ajax
    public List<ItemMgtOpt> getItemMgtOptList(int item_no);

    // 옵션삭제 ajax
    public void deleteItemMgtOptAjax(int opt_no);

    // 옵션삭제 (상품삭제시 옵션도 삭제하기 위함)
    public void deleteItemMgtOpt(int item_no);

}
