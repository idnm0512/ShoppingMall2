package com.thisfit.shoppingmall.user.order.repository.datasource;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

    // 상품 주문 리스트
    @Query("select new com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder(" +
                                "o.orderNo, o.itemNo, o.qty, o.userId, o.opt, o.orderState, o.orderDate, o.orderCancelDate, " +
                                "i.price, i.discount, i.name, i.thumbnail) " +
             "from Order o " +
             "join Item i on o.itemNo = i.no " +
            "where o.userId = :userId and o.orderState = :orderState " +
           "order by o.orderNo desc")
    List<ItemInOrder> findOrderItemList(@Param("userId") String user_id,
                                        @Param("orderState") String order_state);

    // 선택 상품 주문취소 (상태변경)을 위한 조회
    Order findByOrderNo(int order_no);

    // 전체 상품 주문취소 (상태변경)을 위한 조회
    List<Order> findByUserIdAndOrderState(String user_id, String order_state);

}
