package com.thisfit.shoppingmall.user.item.controller;

import java.io.IOException;

import com.thisfit.shoppingmall.admin.itemmgt.domain.usecase.ItemMgtUseCase;
import com.thisfit.shoppingmall.user.item.domain.dto.ItemReviewRequest;
import com.thisfit.shoppingmall.user.item.domain.usecase.ItemReviewUseCase;
import com.thisfit.shoppingmall.user.item.domain.usecase.ItemUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/item")
public class ItemController {

	public static final Logger log = LoggerFactory.getLogger(ItemController.class);

	private final ItemUseCase itemUseCase;
	private final ItemMgtUseCase itemMgtUseCase;
	private final ItemReviewUseCase itemReviewUseCase;

	// 상품 리스트
	@GetMapping("/list")
	public String getItemList(@PageableDefault(size = 30, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
							  String category, String category2, Model model) {
		log.info("상품 리스트");

		model.addAttribute("category", category);
		model.addAttribute("category2", category2);
		model.addAttribute("itemListInfo", itemUseCase.getItemList(category, category2, pageable));
		model.addAttribute("pageMaker", itemUseCase.pagingItem(category, category2, pageable));

		return "item/itemList";
	}
	
	// 상품 디테일
	@GetMapping("/detail")
	public String getItemDetail(int no, Model model) {
		log.info("상품 디테일");
		
		model.addAttribute("itemDetailInfo", itemUseCase.getItemDetail(no));
		model.addAttribute("itemOptInfo", itemMgtUseCase.getItemMgtOptList(no));
		model.addAttribute("itemReviewInfo", itemReviewUseCase.getItemReviewList(no));
		
		return "item/itemDetail";
	}
	
	// 상품 리뷰 팝업창 띄우기
	@GetMapping("/insertReview")
	public String startInsertItemReviewPopup(int no, Model model) {
		log.info("상품 리뷰 팝업창 띄우기");
		
		model.addAttribute("itemDetailInfo", itemUseCase.getItemDetail(no));
		
		return "item/itemReviewInsert";
	}
	
	// 상품 리뷰 등록하기
	@ResponseBody
	@PostMapping("/insertReview")
	public void insertItemReview(ItemReviewRequest itemReviewRequest) {
		try {
			itemReviewUseCase.insertItemReview(itemReviewRequest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IllegalStateException 발생 (상품 리뷰 등록하기)");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IOException 발생 (상품 리뷰 등록하기)");
		}
	}
	
	// 상품 리뷰 수정 팝업창 띄우기
	@GetMapping("/modifyReview")
	public String startModifyItemReviewPopup(int no, int reviewNo, Model model) {
		log.info("상품 리뷰 수정 팝업창 띄우기");
		
		model.addAttribute("itemDetailInfo", itemUseCase.getItemDetail(no));
		model.addAttribute("itemReviewInfo", itemReviewUseCase.getItemReviewDetail(reviewNo));
		
		return "item/itemReviewModify";
	}
	
	// 상품 리뷰 수정하기
	@ResponseBody
	@PostMapping("/modifyReview")
	public void modifyItemReview(ItemReviewRequest itemReviewRequest) {
		try {
			itemReviewUseCase.modifyItemReview(itemReviewRequest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IllegalStateException 발생 (상품 리뷰 수정하기)");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IOException 발생 (상품 리뷰 수정하기)");
		}
	}
	
	// 상품 리뷰 삭제하기
	@ResponseBody
	@PostMapping("/deleteReview")
	public void deleteItemReview(int reviewNo, String reviewImg) {
		itemReviewUseCase.deleteItemReview(reviewNo, reviewImg);
	}
	
}
