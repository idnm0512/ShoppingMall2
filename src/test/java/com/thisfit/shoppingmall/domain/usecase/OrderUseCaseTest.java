package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.item.repository.datasource.Item;
import com.thisfit.shoppingmall.user.item.repository.datasource.ItemJpaRepository;
import com.thisfit.shoppingmall.user.order.domain.usecase.OrderUseCase;
import com.thisfit.shoppingmall.user.order.domain.vo.ItemInOrderVO;
import com.thisfit.shoppingmall.user.order.repository.datasource.Order;
import com.thisfit.shoppingmall.user.order.repository.datasource.OrderJpaRepository;
import net.sf.json.JSONArray;
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
public class OrderUseCaseTest {

    @Autowired
    private OrderUseCase orderUseCase;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    // orderJpaRepository.save
    private int itemNo = 1;
    private int qty = 5;

    private String userId = "id123";
    private String opt = "opt";
    private String orderState = "배송준비중";

    // itemJpaRepository.save
    private int price = 10000;
    private int discount = 10;

    private String name = "name";
    private String thumbnail = "thumbnail";

    @Before
    public void saveOrder() {
        orderJpaRepository.save(Order.builder()
                                     .itemNo(itemNo)
                                     .qty(qty)
                                     .userId(userId)
                                     .opt(opt)
                                     .orderState(orderState)
                                     .build());
    }

    @After
    public void cleanup() {
        orderJpaRepository.deleteAll();
    }

    // 상품 리스트
    @Test
    public void getOrderItemListTest() {
        // given
        itemJpaRepository.save(Item.builder()
                                   .price(price)
                                   .discount(discount)
                                   .name(name)
                                   .thumbnail(thumbnail)
                                   .build());

        // when
        List<ItemInOrderVO> itemInOrderVOList = orderUseCase.getOrderItemList(userId, orderState);

        // then
        ItemInOrderVO itemInOrderVO =  itemInOrderVOList.get(0);

        assertThat(itemInOrderVO.getItemNo()).isEqualTo(itemNo);
        assertThat(itemInOrderVO.getQty()).isEqualTo(qty);
        assertThat(itemInOrderVO.getUserId()).isEqualTo(userId);
        assertThat(itemInOrderVO.getOpt()).isEqualTo(opt);
        assertThat(itemInOrderVO.getPrice()).isEqualTo(price);
        assertThat(itemInOrderVO.getDiscount()).isEqualTo(discount);
        assertThat(itemInOrderVO.getName()).isEqualTo(name);
        assertThat(itemInOrderVO.getThumbnail()).isEqualTo(thumbnail);
    }

    // 상품 구매하기
    @Test
    public void buyItemTest() {
        // given
        Map<String, Object> map = new HashMap<>();

        map.put("qty", qty);
        map.put("name", opt);

        JSONArray selectedOptions = new JSONArray();

        selectedOptions.add(map);

        // when
        orderUseCase.buyItem(itemNo, userId, String.valueOf(selectedOptions));

        // then
        List<Order> orderList = orderJpaRepository.findAll();

        Order order = orderList.get(1);

        assertThat(order.getItemNo()).isEqualTo(itemNo);
        assertThat(order.getQty()).isEqualTo(qty);
        assertThat(order.getUserId()).isEqualTo(userId);
        assertThat(order.getOpt()).isEqualTo(opt);
        assertThat(order.getOrderState()).isEqualTo("배송준비중");
    }

    // 선택 상품 취소 (상태변경)
    @Test
    public void cancelOrderTest() {
        // given
        int orderNo = orderJpaRepository.findLastOrderNo();

        // when
        orderUseCase.cancelOrder(orderNo);

        // then
        List<Order> orderList = orderJpaRepository.findAll();

        Order order = orderList.get(0);
        
        assertThat(order.getOrderState()).isEqualTo("주문취소");
    }

    // 전체 상품 취소 (상태변경)
    @Test
    public void cancelAllOrderTest() {
        // given + when
        orderUseCase.cancelAllOrder(userId);

        // then
        List<Order> orderList = orderJpaRepository.findAll();

        Order order = orderList.get(0);

        assertThat(order.getOrderState()).isEqualTo("주문취소");
    }
}
