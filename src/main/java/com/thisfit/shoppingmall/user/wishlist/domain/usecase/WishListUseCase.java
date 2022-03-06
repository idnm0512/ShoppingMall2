package com.thisfit.shoppingmall.user.wishlist.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.util.paging.PageMaker;
import com.thisfit.shoppingmall.user.wishlist.domain.dto.ItemInWishList;
import com.thisfit.shoppingmall.user.wishlist.domain.dto.WishListRequest;
import com.thisfit.shoppingmall.user.wishlist.domain.repository.WishListGateway;
import com.thisfit.shoppingmall.user.wishlist.domain.vo.ItemInWishListVO;
import com.thisfit.shoppingmall.user.wishlist.domain.vo.WishListInsertVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WishListUseCase {

	private final WishListGateway wishListGateway;

	// 관심상품 리스트
	public List<ItemInWishListVO> getWishList(String userId, Pageable pageable) {
		List<ItemInWishList> itemInWishDTOList = wishListGateway.getWishList(userId,
																			 pageable);
		
		List<ItemInWishListVO> itemInWishVOList = new ArrayList<>();
		
		for (int i = 0; i < itemInWishDTOList.size(); i++) {
			ItemInWishList itemInWishList = itemInWishDTOList.get(i);
			
			ItemInWishListVO itemInWishVO =
									new ItemInWishListVO(itemInWishList.getWishlistNo(),
														 itemInWishList.getItemNo(),
														 itemInWishList.getPrice(),
														 itemInWishList.getDiscount(),
														 itemInWishList.getUserId(),
														 itemInWishList.getName(),
														 itemInWishList.getThumbnail());

			itemInWishVO.calculatePrice();
			
			itemInWishVOList.add(itemInWishVO);
		}
		
		return itemInWishVOList;
	}
	
	// 관심상품 등록
	public void addItemInWishList(WishListRequest wishListRequest) {
		WishListInsertVO wishListInsertVO =
									new WishListInsertVO(wishListRequest.getItemNo(),
														 wishListRequest.getUserId());

		wishListGateway.addItemInWishList(wishListInsertVO);
	}
	
	// 관심상품 선택 삭제
	public void deleteItemInWishList(int wishlistNo) {
		wishListGateway.deleteItemInWishList(wishlistNo);
	}
	
	// 관심상품 전체 삭제 (관심상품 비우기)
	public void deleteAllItemInWishList(String userId) {
		wishListGateway.deleteAllItemInWishList(userId);
	}
	
	// 관심상품 페이징 처리
	public PageMaker pagingWishList(String userId, Pageable pageable) {
		int total = wishListGateway.getTotalWishList(userId);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
