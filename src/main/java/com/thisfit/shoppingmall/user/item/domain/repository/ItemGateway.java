package com.thisfit.shoppingmall.user.item.domain.repository;

import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemGateway {

	// 상품 리스트
	public Page<Item> getItemList(String category, String category2, Pageable pageable);
	
	// 전체 상품 수
	public int getTotalItem(String category, String category2);
	
	// 상품 디테일
	public Item getItemDetail(int no);
	
}
