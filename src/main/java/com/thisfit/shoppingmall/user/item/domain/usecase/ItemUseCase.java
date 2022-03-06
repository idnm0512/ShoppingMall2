package com.thisfit.shoppingmall.user.item.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.user.item.domain.repository.ItemGateway;
import com.thisfit.shoppingmall.user.item.domain.vo.*;
import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemUseCase {

	private final ItemGateway itemGateway;

	// 상품 리스트
	public List<ItemVO> getItemList(String category, String category2, Pageable pageable) {
		Page<Item> itemList = itemGateway.getItemList(category,
													  category2,
												      pageable);

		List<ItemVO> itemVOList = new ArrayList<>();

		for (int i = 0; i < itemList.getContent().size(); i++) {

			Item item = itemList.getContent().get(i);

			ItemVO itemVO = new ItemVO(item.getNo(),
									   item.getPrice(),
									   item.getDiscount(),
									   item.getCategory(),
									   item.getCategory2(),
									   item.getName(),
									   item.getThumbnail(),
									   item.getContent());

			itemVO.calculatePrice();

			itemVOList.add(itemVO);
		}
		
		return itemVOList;
	}
	
	// 상품 디테일
	public ItemDetailVO getItemDetail(int no) {
		Item item = itemGateway.getItemDetail(no);
		
		ItemDetailVO itemDetailVO = new ItemDetailVO(no,
													 item.getPrice(),
													 item.getDiscount(),
													 item.getCategory(),
													 item.getCategory2(),
													 item.getName(),
													 item.getThumbnail(),
													 item.getContent());

		itemDetailVO.calculatePrice();
		
		return itemDetailVO;
	}

	// 상품페이지 페이징 처리
	public PageMaker pagingItem(String category, String category2, Pageable pageable) {
		int total = itemGateway.getTotalItem(category, category2);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
