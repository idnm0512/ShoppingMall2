package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemJpaRepository;
import com.thisfit.shoppingmall.user.wishlist.domain.dto.WishListRequest;
import com.thisfit.shoppingmall.user.wishlist.domain.usecase.WishListUseCase;
import com.thisfit.shoppingmall.user.wishlist.domain.vo.ItemInWishListVO;
import com.thisfit.shoppingmall.user.wishlist.repository.datasource.WishList;
import com.thisfit.shoppingmall.user.wishlist.repository.datasource.WishListJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WishListUseCaseTest {

    @Autowired
    private WishListUseCase wishListUseCase;

    @Autowired
    private WishListJpaRepository wishListJpaRepository;

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    // wishListJpaRepository.save
    private int itemNo = 1;

    private String userId = "id123";

    // itemJpaRepository.save
    private int price = 10000;
    private int discount = 10;

    private String name = "name";
    private String thumbnail = "thumbnail";

    @Before
    public void saveWishList() {
        wishListJpaRepository.save(WishList.builder()
                                           .itemNo(itemNo)
                                           .userId(userId)
                                           .build());
    }

    @After
    public void cleanup() {
        wishListJpaRepository.deleteAll();
    }

    // 관심상품 리스트
    @Test
    public void getWishListTest() {
        // given
        itemJpaRepository.save(Item.builder()
                                   .price(price)
                                   .discount(discount)
                                   .name(name)
                                   .thumbnail(thumbnail)
                                   .build());

        Pageable pageable = PageRequest.of(0, 30);

        // when
        List<ItemInWishListVO> itemInWishVOList = wishListUseCase.getWishList(userId, pageable);

        // then
        ItemInWishListVO itemInWishVO = itemInWishVOList.get(0);

        assertThat(itemInWishVO.getItemNo()).isEqualTo(itemNo);
        assertThat(itemInWishVO.getPrice()).isEqualTo(price);
        assertThat(itemInWishVO.getDiscount()).isEqualTo(discount);
        assertThat(itemInWishVO.getUserId()).isEqualTo(userId);
        assertThat(itemInWishVO.getName()).isEqualTo(name);
        assertThat(itemInWishVO.getThumbnail()).isEqualTo(thumbnail);
    }

    // 관심상품 등록
    @Test
    public void addItemInWishListTest() {
        // given
        WishListRequest wishListRequest = new WishListRequest();

        wishListRequest.setItemNo(itemNo);
        wishListRequest.setUserId(userId);

        // when
        wishListUseCase.addItemInWishList(wishListRequest);

        // then
        List<WishList> wishlistList = wishListJpaRepository.findAll();

        WishList wishList = wishlistList.get(1);

        assertThat(wishList.getItemNo()).isEqualTo(itemNo);
        assertThat(wishList.getUserId()).isEqualTo(userId);
    }

    // 관심상품 선택 삭제
    @Test
    public void deleteItemInWishListTest() {
        // given
        int wishlistNo = wishListJpaRepository.findLastWishlistNo();

        // when
        wishListUseCase.deleteItemInWishList(wishlistNo);

        // then
        List<WishList> wishlistList = wishListJpaRepository.findAll();

        assertThat(wishlistList).isEmpty();
    }

    // 관심상품 전체 삭제 (관심상품 비우기)
    @Test
    public void deleteAllItemInWishListTest() {
        // given + when
        wishListUseCase.deleteAllItemInWishList(userId);

        // then
        List<WishList> wishlistList = wishListJpaRepository.findAll();

        assertThat(wishlistList).isEmpty();
    }

}
