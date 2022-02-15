package com.thisfit.shoppingmall.user.wishlist.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.wishlist.domain.dto.ItemInWishList;
import com.thisfit.shoppingmall.user.wishlist.domain.repository.WishListGateway;
import com.thisfit.shoppingmall.user.wishlist.domain.vo.WishListInsertVO;
import com.thisfit.shoppingmall.user.wishlist.repository.datasource.WishList;
import com.thisfit.shoppingmall.user.wishlist.repository.datasource.WishListJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishListJpa implements WishListGateway {

	@Autowired
	private WishListJpaRepository wishListJpaRepository;
	
	// 관심상품 리스트
	@Override
	public List<ItemInWishList> getWishList(String user_id, Pageable pageable) {
		return wishListJpaRepository.findWishList(user_id, pageable);
	}
	
	// 전체 관심상품 수
	@Override
	public int getTotalWishList(String user_id) {
		return wishListJpaRepository.countByUserId(user_id);
	}
	
	// 관심상품 등록
	@Override
	@Modifying
	@Transactional
	public void addItemInWishList(WishListInsertVO addItemInWishListVO) {
		WishList wishList = WishList.builder()
									.userId(addItemInWishListVO.getUser_id())
									.itemNo(addItemInWishListVO.getItem_no())
									.build();

		wishListJpaRepository.save(wishList);
	}
	
	// 관심상품 선택 삭제
	@Override
	@Modifying
	@Transactional
	public void deleteItemInWishList(int wishlist_no) {
		wishListJpaRepository.deleteById(wishlist_no);
	}
	
	// 관심상품 전체 삭제 (관심상품 비우기)
	@Override
	@Modifying
	@Transactional
	public void deleteAllItemInWishList(String user_id) {
		wishListJpaRepository.deleteByUserId(user_id);
	}
	
}
