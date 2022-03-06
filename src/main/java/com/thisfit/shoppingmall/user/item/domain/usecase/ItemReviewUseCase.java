package com.thisfit.shoppingmall.user.item.domain.usecase;

import com.thisfit.shoppingmall.user.item.domain.dto.ItemReviewRequest;
import com.thisfit.shoppingmall.user.item.domain.repository.ItemReviewGateway;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewInfoVO;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewInsertVO;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemReviewModifyVO;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemReview;
import com.thisfit.shoppingmall.util.s3.S3FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemReviewUseCase {

    private final ItemReviewGateway itemReviewGateway;
    private final S3FileUpload s3FileUpload;

    // 상품 리뷰 리스트
    public List<ItemReviewInfoVO> getItemReviewList(int itemNo) {
        List<ItemReview> itemReviewList = itemReviewGateway.getItemReviewList(itemNo);

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
    public ItemReviewInfoVO getItemReviewDetail(int reviewNo) {
        ItemReview itemReview = itemReviewGateway.getItemReviewDetail(reviewNo);

        ItemReviewInfoVO itemReviewInfoVO = new ItemReviewInfoVO(itemReview.getReviewNo(),
                itemReview.getItemNo(),
                itemReview.getGrade(),
                itemReview.getUserId(),
                itemReview.getContent(),
                itemReview.getReviewImg(),
                itemReview.getUpdateDate());

        return itemReviewInfoVO;
    }

    // 이미지 존재여부 이름으로 체크
    public boolean isImgNameEmpty(String imgName) {
        return imgName == null || imgName.isEmpty();
    }

    // 상품 리뷰 등록하기
    public void insertItemReview(ItemReviewRequest itemReviewRequest) throws IllegalStateException, IOException {
        String url = itemReviewRequest.getReviewImg();

        if (!isImgNameEmpty(itemReviewRequest.getReviewImgFile().getOriginalFilename())) {
            url = s3FileUpload.imgUpLoad(itemReviewRequest.getReviewImgFile(), "review/");
        }

        ItemReviewInsertVO itemReviewInsertVO =
                                        new ItemReviewInsertVO(itemReviewRequest.getItemNo(),
                                                               itemReviewRequest.getGrade(),
                                                               itemReviewRequest.getUserId(),
                                                               itemReviewRequest.getContent(),
                                                               url);

        itemReviewGateway.insertItemReview(itemReviewInsertVO);
    }

    // 상품 리뷰 수정하기
    public void modifyItemReview(ItemReviewRequest itemReviewRequest) throws IllegalStateException, IOException {
        String url = itemReviewRequest.getReviewImg();

        if (!isImgNameEmpty(itemReviewRequest.getReviewImgFile().getOriginalFilename())) {
            if (!isImgNameEmpty(url)) s3FileUpload.imgDelete(url, "review/");

            url = s3FileUpload.imgUpLoad(itemReviewRequest.getReviewImgFile(), "review/");
        }

        ItemReviewModifyVO itemReviewModifyVO =
                                        new ItemReviewModifyVO(itemReviewRequest.getReviewNo(),
                                                               itemReviewRequest.getGrade(),
                                                               itemReviewRequest.getContent(),
                                                               url);

        itemReviewGateway.modifyItemReview(itemReviewModifyVO);
    }

    // 상품 리뷰 삭제하기
    public void deleteItemReview(int reviewNo, String reviewImg) {
        if (!isImgNameEmpty(reviewImg)) s3FileUpload.imgDelete(reviewImg, "review/");

        itemReviewGateway.deleteItemReview(reviewNo);
    }

}
