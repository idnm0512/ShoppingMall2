package com.thisfit.shoppingmall.admin.itemmgt.domain.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thisfit.shoppingmall.admin.itemmgt.domain.dto.ItemMgtRequest;
import com.thisfit.shoppingmall.admin.itemmgt.domain.repository.ItemMgtGateway;
import com.thisfit.shoppingmall.admin.itemmgt.domain.repository.ItemMgtOptGateway;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtInsertVO;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtModifyVO;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtOptInsertVO;
import com.thisfit.shoppingmall.admin.itemmgt.repository.datasource.ItemMgtOpt;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtOptInfoVO;
import com.thisfit.shoppingmall.util.s3.S3FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@RequiredArgsConstructor
@Component
public class ItemMgtUseCase {
	
	private final ItemMgtGateway itemMgtGateway;
	private final ItemMgtOptGateway itemMgtOptGateway;
	private final S3FileUpload s3FileUpload;

	// 이미지 존재여부 이름으로 체크
	public boolean isImgNameEmpty(String imgName) {
		return imgName == null || imgName.isEmpty();
	}

	// 상품 등록
	public void insertItemMgt(ItemMgtRequest itemMgtRequest, String[] opt_array) throws IllegalStateException, IOException {
		String url = s3FileUpload.imgUpLoad(itemMgtRequest.getThumbnail_file(), "thumbnail/");

		ItemMgtInsertVO itemMgtInsertVO = new ItemMgtInsertVO(itemMgtRequest.getPrice(),
															  itemMgtRequest.getDiscount(),
															  itemMgtRequest.getCategory(),
															  itemMgtRequest.getCategory2(),
															  itemMgtRequest.getName(),
															  itemMgtRequest.getContent(),
															  url);


		itemMgtGateway.insertItemMgt(itemMgtInsertVO);

		// 방금 등록한 상품의 번호 가져오기
		int item_no = itemMgtGateway.getItemNo();
		
		// 옵션 등록
		insertItemMgtOpt(opt_array, item_no);
	}
	
	// 상품 수정
	public void modifyItemMgt(ItemMgtRequest itemMgtRequest, String[] opt_array) throws IllegalStateException, IOException {
		String url = itemMgtRequest.getThumbnail();

		if (!isImgNameEmpty(itemMgtRequest.getThumbnail_file().getOriginalFilename())) {
			s3FileUpload.imgDelete(url, "thumbnail/");

			url = s3FileUpload.imgUpLoad(itemMgtRequest.getThumbnail_file(), "thumbnail/");
		}
		
		ItemMgtModifyVO itemMgtModifyVO = new ItemMgtModifyVO(itemMgtRequest.getNo(),
															  itemMgtRequest.getPrice(),
															  itemMgtRequest.getDiscount(),
															  itemMgtRequest.getCategory(),
															  itemMgtRequest.getCategory2(),
															  itemMgtRequest.getName(),
															  itemMgtRequest.getContent(),
															  url);

		itemMgtGateway.modifyItemMgt(itemMgtModifyVO);
		
		// 옵션 등록
		if (opt_array != null) insertItemMgtOpt(opt_array, itemMgtRequest.getNo());
	}
	
	// 상품 삭제
	public void deleteItemMgt(int no, String thumbnail) {
		itemMgtGateway.deleteItemMgt(no);
		
		// 옵션 삭제
		deleteItemMgtOpt(no);
		
		// 썸네일 삭제
		s3FileUpload.imgDelete(thumbnail, "thumbnail/");
	}
	
	// 옵션 등록 (상품 등록 - insertItemUC()에서 사용됨)
	public void insertItemMgtOpt(String[] opt_array, int item_no) {
		for (int i = 0; i < opt_array.length; i++) {

			String[] arr = opt_array[i].split("/");

			for (int j = 0; j <arr.length;) {
				int qty = Integer.parseInt(arr[j + 2]);
				String size = arr[j + 1];
				String color = arr[j];
				
				ItemMgtOptInsertVO itemMgtOptInsertVO =
												new ItemMgtOptInsertVO(item_no,
																	   qty,
																	   color,
																	   size);

				itemMgtOptGateway.insertItemMgtOpt(itemMgtOptInsertVO);
				
				break;
			}
		}
	}
	
	// 옵션 리스트 ajax
	public List<ItemMgtOptInfoVO> getItemMgtOptList(int item_no) {
		List<ItemMgtOpt> itemMgtOptList = itemMgtOptGateway.getItemMgtOptList(item_no);
		
		List<ItemMgtOptInfoVO> itemMgtOptInfoVOList = new ArrayList<>();
		
		for (int i = 0; i < itemMgtOptList.size(); i++) {
			ItemMgtOpt itemMgtOpt = itemMgtOptList.get(i);
			
			ItemMgtOptInfoVO itemMgtOptInfoVO = new ItemMgtOptInfoVO(itemMgtOpt.getOptNo(),
																	 itemMgtOpt.getQty(),
																	 itemMgtOpt.getColor(),
																	 itemMgtOpt.getSize());
			itemMgtOptInfoVOList.add(itemMgtOptInfoVO);
		}
		
		return itemMgtOptInfoVOList;
	}
	
	// 옵션 삭제 ajax
	public void deleteItemMgtOptAjax(int opt_no) {
		itemMgtOptGateway.deleteItemMgtOptAjax(opt_no);
	}
	
	// 옵션 삭제 (상품삭제시 옵션도 삭제하기 위함)
	public void deleteItemMgtOpt(int item_no) {
		itemMgtOptGateway.deleteItemMgtOpt(item_no);
	}
	
	// 썸머노트 이미지 업로드 ajax
	public String uploadSummerNoteEditorImg(MultipartFile multipartFile) {
		JsonObject jsonObject = new JsonObject();

		try {
			String url = s3FileUpload.imgUpLoad(multipartFile, "content/");

			jsonObject.addProperty("url", url);
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IOException 발생 (썸머노트 이미지 업로드 ajax)");
		}

		String jsonInfo = jsonObject.toString();
		
		return jsonInfo;
	}
	
}
