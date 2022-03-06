package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.cart.domain.usecase.CartUseCase;
import com.thisfit.shoppingmall.user.cart.domain.vo.ItemInCartVO;
import com.thisfit.shoppingmall.user.cart.repository.datasource.Cart;
import com.thisfit.shoppingmall.user.cart.repository.datasource.CartJpaRepository;
import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemJpaRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartUseCaseTest {

    @Autowired
    CartUseCase cartUseCase;

    @Autowired
    CartJpaRepository cartJpaRepository;

    @Autowired
    ItemJpaRepository itemJpaRepository;

    // cartJpaRepository.save
    private int itemNo = 1;
    private int qty = 5;

    private String userId = "id123";
    private String opt = "opt";

    // itemJpaRepository.save
    private int price = 10000;
    private int discount = 10;

    private String name = "name";
    private String thumbnail = "thumbnail";

    @Before
    public void saveCart() {
        cartJpaRepository.save(Cart.builder()
                                   .userId(userId)
                                   .itemNo(itemNo)
                                   .opt(opt)
                                   .qty(qty)
                                   .build());
    }

    @After
    public void cleanup() {
        cartJpaRepository.deleteAll();
    }

    // 장바구니 리스트
    @Test
    public void getCartListTest() {
        // given + when
        itemJpaRepository.save(Item.builder()
                                   .price(price)
                                   .discount(discount)
                                   .name(name)
                                   .thumbnail(thumbnail)
                                   .build());

        List<ItemInCartVO> itemInCartVOList = cartUseCase.getCartList(userId);

        // then
        ItemInCartVO itemInCartVO = itemInCartVOList.get(0);

        assertThat(itemInCartVO.getUserId()).isEqualTo(userId);
        assertThat(itemInCartVO.getItemNo()).isEqualTo(itemNo);
        assertThat(itemInCartVO.getOpt()).isEqualTo(opt);
        assertThat(itemInCartVO.getQty()).isEqualTo(qty);
        assertThat(itemInCartVO.getPrice()).isEqualTo(price);
        assertThat(itemInCartVO.getDiscount()).isEqualTo(discount);
        assertThat(itemInCartVO.getName()).isEqualTo(name);
        assertThat(itemInCartVO.getThumbnail()).isEqualTo(thumbnail);
    }

    // 장바구니 담기
    @Test
    public void addItemInCartTest() {
        // given
        Map<String, Object> map = new HashMap<>();

        map.put("qty", qty);
        map.put("name", opt);

        JSONArray selectedOptions = new JSONArray();

        selectedOptions.add(map);

        // when
        cartUseCase.addItemInCart(itemNo, userId, String.valueOf(selectedOptions));

        // then
        List<Cart> cartList = cartJpaRepository.findAll();

        Cart cart = cartList.get(1);

        assertThat(cart.getItemNo()).isEqualTo(itemNo);
        assertThat(cart.getUserId()).isEqualTo(userId);
        assertThat(cart.getOpt()).isEqualTo(opt);
        assertThat(cart.getQty()).isEqualTo(qty);
    }

    // 장바구니 상품 수량 변경
    @Test
    public void modifyItemQtyTest() {
        // given
        int cartNo = cartJpaRepository.findLastCartNo();

        qty = 3;

        // when
        cartUseCase.modifyItemQty(qty, cartNo);

        // then
        List<Cart> cartList = cartJpaRepository.findAll();

        Cart cart = cartList.get(0);

        assertThat(cart.getQty()).isEqualTo(qty);
    }

    // 장바구니 선택 상품 삭제
    @Test
    public void deleteItemInCartTest() {
        // given
        int cartNo = cartJpaRepository.findLastCartNo();

        // when
        cartUseCase.deleteItemInCart(cartNo);

        // then
        List<Cart> cartList = cartJpaRepository.findAll();

        assertThat(cartList).isEmpty();
    }

    // 장바구니 전체 상품 삭제 (장바구니 비우기)
    @Test
    public void deleteAllItemInCart() {
        // given + when
        cartUseCase.deleteAllItemInCart(userId);

        // then
        List<Cart> cartList = cartJpaRepository.findAll();

        assertThat(cartList).isEmpty();
    }

}
