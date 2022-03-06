package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.item.domain.usecase.ItemUseCase;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemDetailVO;
import com.thisfit.shoppingmall.user.item.domain.vo.ItemVO;
import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemJpaRepository;
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
public class ItemUseCaseTest {

    @Autowired
    private ItemUseCase itemUseCase;

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    private int price = 10000;
    private int discount = 10;

    private String category = "category";
    private String category2 = "category2";
    private String name = "name";
    private String thumbnail = "thumbnail";
    private String content = "content";

    @Before
    public void saveItem() {
        Item item = Item.builder()
                        .price(price)
                        .discount(discount)
                        .category(category)
                        .category2(category2)
                        .name(name)
                        .thumbnail(thumbnail)
                        .content(content)
                        .build();

        itemJpaRepository.save(item);
    }

    @After
    public void cleanup() {
        itemJpaRepository.deleteAll();
    }

    // 상품 리스트
    @Test
    public void getItemListTest() {
        // given
        Pageable pageable = PageRequest.of(0, 30);

        // when
        List<ItemVO> itemVOList = itemUseCase.getItemList(category, category2, pageable);

        // then
        ItemVO itemVO = itemVOList.get(0);

        assertThat(itemVO.getPrice()).isEqualTo(price);
        assertThat(itemVO.getDiscount()).isEqualTo(discount);
        assertThat(itemVO.getCategory()).isEqualTo(category);
        assertThat(itemVO.getCategory2()).isEqualTo(category2);
        assertThat(itemVO.getName()).isEqualTo(name);
        assertThat(itemVO.getThumbnail()).isEqualTo(thumbnail);
        assertThat(itemVO.getContent()).isEqualTo(content);
    }

    // 상품 디테일
    @Test
    public void getItemDetailTest() {
        // given
        int no = itemJpaRepository.findLastNo();

        // when
        ItemDetailVO itemDetailVO = itemUseCase.getItemDetail(no);

        // then
        assertThat(itemDetailVO.getPrice()).isEqualTo(price);
        assertThat(itemDetailVO.getDiscount()).isEqualTo(discount);
        assertThat(itemDetailVO.getCategory()).isEqualTo(category);
        assertThat(itemDetailVO.getCategory2()).isEqualTo(category2);
        assertThat(itemDetailVO.getName()).isEqualTo(name);
        assertThat(itemDetailVO.getThumbnail()).isEqualTo(thumbnail);
        assertThat(itemDetailVO.getContent()).isEqualTo(content);
    }

}
