package com.thisfit.shoppingmall.user.wishlist.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.wishlist.domain.dto.ItemInWishList;
import com.thisfit.shoppingmall.user.wishlist.domain.repository.WishListGateway;
import com.thisfit.shoppingmall.user.wishlist.domain.vo.WishListInsertVO;
import com.thisfit.shoppingmall.user.wishlist.repository.datasource.WishList;
import com.thisfit.shoppingmall.user.wishlist.repository.datasource.WishListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WishListJpa implements WishListGateway {

	private final WishListJpaRepository wishListJpaRepository;
	
	// 관심상품 리스트
	@Override
	public List<ItemInWishList> getWishList(String userId, Pageable pageable) {
		return wishListJpaRepository.findWishList(userId, pageable);
	}
	
	// 전체 관심상품 수
	@Override
	public int getTotalWishList(String userId) {
		return wishListJpaRepository.countByUserId(userId);
	}
	
	// 관심상품 등록
	@Override
	@Modifying
	@Transactional
	public void addItemInWishList(WishListInsertVO addItemInWishListVO) {
		WishList wishList = WishList.builder()
									.userId(addItemInWishListVO.getUserId())
									.itemNo(addItemInWishListVO.getItemNo())
									.build();

		wishListJpaRepository.save(wishList);
	}
	
	// 관심상품 선택 삭제
	@Override
	@Modifying
	@Transactional
	public void deleteItemInWishList(int wishlistNo) {
		wishListJpaRepository.deleteById(wishlistNo);
	}
	
	// 관심상품 전체 삭제 (관심상품 비우기)
	@Override
	@Modifying
	@Transactional
	public void deleteAllItemInWishList(String userId) {
		wishListJpaRepository.deleteByUserId(userId);
	}
	
}
