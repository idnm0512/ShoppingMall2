package com.thisfit.shoppingmall.admin.itemmgt.repository;

import com.thisfit.shoppingmall.admin.itemmgt.domain.repository.ItemMgtGateway;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtInsertVO;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtModifyVO;
import com.thisfit.shoppingmall.admin.itemmgt.repository.datasource.ItemMgtJpaRepository;
import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemMgtJpa implements ItemMgtGateway {

	private final ItemMgtJpaRepository itemMgtJpaRepository;
	
	// 상품등록
	@Override
	@Modifying
	@Transactional
	public void insertItemMgt(ItemMgtInsertVO itemMgtInsertVO) {
		Item item = Item.builder()
					    .category(itemMgtInsertVO.getCategory())
					    .category2(itemMgtInsertVO.getCategory2())
					    .name(itemMgtInsertVO.getName())
					    .price(itemMgtInsertVO.getPrice())
					    .discount(itemMgtInsertVO.getDiscount())
					    .thumbnail(itemMgtInsertVO.getThumbnail())
					    .content(itemMgtInsertVO.getContent())
					    .build();

		itemMgtJpaRepository.save(item);
	}
	
	// 상품수정
	@Override
	@Modifying
	@Transactional
	public void modifyItemMgt(ItemMgtModifyVO itemMgtModifyVO) {
		Item item = itemMgtJpaRepository.findByNo(itemMgtModifyVO.getNo());

		item.changeItem(itemMgtModifyVO.getPrice(),
						itemMgtModifyVO.getDiscount(),
						itemMgtModifyVO.getCategory(),
						itemMgtModifyVO.getCategory2(),
						itemMgtModifyVO.getName(),
						itemMgtModifyVO.getThumbnail(),
						itemMgtModifyVO.getContent());

		itemMgtJpaRepository.save(item);
	}
	
	// 상품삭제
	@Override
	@Modifying
	@Transactional
	public void deleteItemMgt(int no) {
		itemMgtJpaRepository.deleteById(no);
	}
	
	// 마지막(방금 등록된) 상품 번호 가져오기 (옵션등록을 위함)
	@Override
	public int getItemNo() {
		return itemMgtJpaRepository.findItemNo();
	}
	
}
