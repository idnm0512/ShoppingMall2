package com.thisfit.shoppingmall.admin.itemmgt.domain.repository;

import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtInsertVO;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtModifyVO;

public interface ItemMgtGateway {

	// 상품등록
	public void insertItemMgt(ItemMgtInsertVO itemMgtInsertVO);
	
	// 상품수정
	public void modifyItemMgt(ItemMgtModifyVO itemMgtModifyVO);
	
	// 상품삭제
	public void deleteItemMgt(int no);
	
	// 마지막(방금 등록된) 상품 번호 가져오기 (옵션등록을 위함)
	public int getItemNo();
	
}
