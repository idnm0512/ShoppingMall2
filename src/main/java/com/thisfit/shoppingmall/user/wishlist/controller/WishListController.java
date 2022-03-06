package com.thisfit.shoppingmall.user.wishlist.controller;

import com.thisfit.shoppingmall.user.wishlist.domain.dto.WishListRequest;
import com.thisfit.shoppingmall.user.wishlist.domain.usecase.WishListUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wishList")
public class WishListController {
	
	public static final Logger log = LoggerFactory.getLogger(WishListController.class);

	private final WishListUseCase wishListUseCase;
	
	// 관심상품 페이지 진입 (리스트)
	@GetMapping
	public String getWishList(@PageableDefault(size = 30, sort = "wishlistNo", direction = Sort.Direction.DESC) Pageable pageable,
							  String userId, Model model) {
		log.info("관심상품 페이지 진입 (리스트)");

		model.addAttribute("userId", userId);
		model.addAttribute("wishListInfo", wishListUseCase.getWishList(userId, pageable));
		model.addAttribute("pageMaker", wishListUseCase.pagingWishList(userId, pageable));

		return "wishList/wishList";
	}
	
	// 관심상품 등록
	@ResponseBody
	@PostMapping("/addItemInWishList")
	public void addItemInWishList(WishListRequest wishListRequest) {
		wishListUseCase.addItemInWishList(wishListRequest);
	}
	
	// 관심상품 선택 삭제
	@ResponseBody
	@PostMapping("/deleteItemInWishList")
	public void deleteItemInWishList(int wishlistNo) {
		wishListUseCase.deleteItemInWishList(wishlistNo);
	}
	
	// 관심상품 전체 삭제 (관심상품 비우기)
	@ResponseBody
	@PostMapping("/deleteAllItemInWishList")
	public void deleteAllItemInWishListPost(String userId) {
		wishListUseCase.deleteAllItemInWishList(userId);
	}
	
}
