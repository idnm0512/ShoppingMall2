package com.thisfit.shoppingmall.user.item.domain.usecase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.thisfit.shoppingmall.user.item.domain.dto.ItemReviewRequest;
import com.thisfit.shoppingmall.user.item.domain.repository.ItemGateway;
import com.thisfit.shoppingmall.user.item.domain.repository.ItemReviewGateway;
import com.thisfit.shoppingmall.user.item.domain.vo.*;
import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReview;
import com.thisfit.shoppingmall.user.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class ItemUseCase {

	private final ItemGateway itemGateway;
	private final ItemReviewGateway itemReviewGateway;

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
	
	// 상품 리뷰 리스트
	public List<ItemReviewInfoVO> getItemReviewList(int item_no) {
		List<ItemReview> itemReviewList = itemReviewGateway.getItemReviewList(item_no);
		
		List<ItemReviewInfoVO> itemReviewInfoVOList = new ArrayList<>();
		
		for (int i = 0; i < itemReviewList.size(); i++) {
			ItemReview itemReview = itemReviewList.get(i);
			
			ItemReviewInfoVO itemReviewInfoVO = new ItemReviewInfoVO(itemReview.getReviewNo(),
																	 itemReview.getItemNo(),
																	 itemReview.getGrade(),
																	 itemReview.getUserId(),
																	 itemReview.getContent(),
																	 itemReview.getReviewImg(),
																	 itemReview.getUpdateDate());
			
			itemReviewInfoVOList.add(itemReviewInfoVO);
		}
		
		return itemReviewInfoVOList;
	}
	
	// 상품 리뷰 디테일
	public ItemReviewInfoVO getItemReviewDetail(int review_no) {
		ItemReview itemReview = itemReviewGateway.getItemReviewDetail(review_no);
		
		ItemReviewInfoVO itemReviewInfoVO = new ItemReviewInfoVO(itemReview.getReviewNo(),
																 itemReview.getItemNo(),
																 itemReview.getGrade(),
																 itemReview.getUserId(),
																 itemReview.getContent(),
																 itemReview.getReviewImg(),
																 itemReview.getUpdateDate());
		
		return itemReviewInfoVO;
	}
	
	// 상품 리뷰 등록하기
	public void insertItemReview(ItemReviewRequest itemReviewRequest) throws IllegalStateException, IOException {
		String reviewImgName = upsertItemReviewImg(itemReviewRequest);
		
		ItemReviewInsertVO itemReviewInsertVO =
									new ItemReviewInsertVO(itemReviewRequest.getReview_no(),
														   itemReviewRequest.getItem_no(),
														   itemReviewRequest.getGrade(),
														   itemReviewRequest.getUser_id(),
														   itemReviewRequest.getContent(),
														   reviewImgName);

		itemReviewGateway.insertItemReview(itemReviewInsertVO);
	}
	
	// 상품 리뷰 수정하기
	public void modifyItemReview(ItemReviewRequest itemReviewRequest) throws IllegalStateException, IOException {
		String reviewImgName = upsertItemReviewImg(itemReviewRequest);
		
		ItemReviewModifyVO itemReviewModifyVO =
									new ItemReviewModifyVO(itemReviewRequest.getReview_no(),
														   itemReviewRequest.getGrade(),
														   itemReviewRequest.getContent(),
														   reviewImgName);

		itemReviewGateway.modifyItemReview(itemReviewModifyVO);
	}
	
	// 상품 리뷰 삭제하기
	public void deleteItemReview(int review_no, String review_img) {
		if (review_img != "") deleteItemReviewImg(review_img);

		itemReviewGateway.deleteItemReview(review_no);
	}
	
	// 상품 이미지 이름 빈 값 체크
	public boolean isImgNameEmpty(String imgName) {
		return imgName == null || imgName.isEmpty();
	}
	
	// 상품 리뷰 이미지 insert, update
	public String upsertItemReviewImg(ItemReviewRequest itemReviewRequest) throws IllegalStateException, IOException {
		MultipartFile reviewImgFile = itemReviewRequest.getReview_img_file();
		
		String prevReviewImgName = itemReviewRequest.getReview_img();

		if (!isImgNameEmpty(reviewImgFile.getOriginalFilename())) {
			
			if (!isImgNameEmpty(prevReviewImgName)) deleteItemReviewImg(prevReviewImgName);

			String originalFileName = reviewImgFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			UUID uuid = UUID.randomUUID();
			String newReviewImgName = "review_img_" + uuid + "." + ext;
			
			reviewImgFile.transferTo(new File("C:/Users/idnm0/IdeaProjects/ShoppingMall/src/main/webapp/resources/review/" + newReviewImgName));
			
			return newReviewImgName;
		}
		
		return prevReviewImgName;
	}
	
	// 상품 리뷰 이미지 delete
	public void deleteItemReviewImg(String review_img) {
		File file = new File("C:/Users/idnm0/IdeaProjects/ShoppingMall/src/main/webapp/resources/review/" + review_img);
		file.delete();
	}

	// 상품페이지 페이징 처리
	public PageMaker pagingItem(String category, String category2, Pageable pageable) {
		int total = itemGateway.getTotalItem(category, category2);

		PageMaker pageMaker = new PageMaker(pageable, total);

		return pageMaker;
	}
	
}
