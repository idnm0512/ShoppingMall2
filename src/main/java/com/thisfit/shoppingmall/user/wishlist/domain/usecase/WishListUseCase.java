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
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WishListUseCase {

	private final WishListGateway wishListGateway;

	// 관심상품 리스트
	public List<ItemInWishListVO> getWishList(String user_id, Pageable pageable) {
		List<ItemInWishList> itemInWishDTOList = wishListGateway.getWishList(user_id,
																			 pageable);
		
		List<ItemInWishListVO> itemInWishVOList = new ArrayList<>();
		
		for (int i = 0; i < itemInWishDTOList.size(); i++) {
			ItemInWishList itemInWishList = itemInWishDTOList.get(i);
			
			ItemInWishListVO itemInWishVO =
									new ItemInWishListVO(itemInWishList.getWishlist_no(),
														 itemInWishList.getItem_no(),
														 itemInWishList.getPrice(),
														 itemInWishList.getDiscount(),
														 itemInWishList.getUser_id(),
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
									new WishListInsertVO(wishListRequest.getItem_no(),
														 wishListRequest.getUser_id());

		wishListGateway.addItemInWishList(wishListInsertVO);
	}
	
	// 관심상품 선택 삭제
	public void deleteItemInWishList(int wishlist_no) {
		wishListGateway.deleteItemInWishList(wishlist_no);
	}
	
	// 관심상품 전체 삭제 (관심상품 비우기)
	public void deleteAllItemInWishList(String user_id) {
		wishListGateway.deleteAllItemInWishList(user_id);
	}
	
	// 관심상품 페이징 처리
	public PageMaker pagingWishList(String user_id, Pageable pageable) {
		int total = wishListGateway.getTotalWishList(user_id);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
