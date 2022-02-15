package com.thisfit.shoppingmall.user.wishlist.domain.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.wishlist.domain.dto.ItemInWishList;
import com.thisfit.shoppingmall.user.wishlist.domain.vo.WishListInsertVO;
import org.springframework.data.domain.Pageable;

public interface WishListGateway {

	// 관심상품 리스트
	public List<ItemInWishList> getWishList(String user_id, Pageable pageable);
	
	// 전체 관심상품 수
	public int getTotalWishList(String user_id);
	
	// 관심상품 등록
	public void addItemInWishList(WishListInsertVO addItemInWishListVO);
	
	// 관심상품 선택 삭제
	public void deleteItemInWishList(int wishlist_no);
	
	// 관심상품 전체 삭제 (관심상품 비우기)
	public void deleteAllItemInWishList(String user_id);
	
}
