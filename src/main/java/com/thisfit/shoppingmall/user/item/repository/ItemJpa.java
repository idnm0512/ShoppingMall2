package com.thisfit.shoppingmall.user.item.repository;

import com.thisfit.shoppingmall.user.item.domain.repository.ItemGateway;
import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemJpa implements ItemGateway {

	@Autowired
	private ItemJpaRepository itemJpaRepository;
	
	// 상품 리스트
	@Override
	public Page<Item> getItemList(String category, String category2, Pageable pageable) {
		return itemJpaRepository.findByCategoryContainsAndCategory2Contains(category, category2, pageable);
	}
	
	// 상품 디테일
	@Override
	public Item getItemDetail(int no) {
		return itemJpaRepository.findByNo(no);
	}

	// 전체 상품 수
	@Override
	public int getTotalItem(String category, String category2) {
		return itemJpaRepository.countByCategoryContainsAndCategory2Contains(category, category2);
	}

}
