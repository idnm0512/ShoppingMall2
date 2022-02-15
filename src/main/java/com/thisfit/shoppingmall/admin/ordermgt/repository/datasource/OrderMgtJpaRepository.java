package com.thisfit.shoppingmall.admin.ordermgt.repository.datasource;

import com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder;
import com.thisfit.shoppingmall.user.order.repository.datasource.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMgtJpaRepository extends JpaRepository<Order, Integer> {

    // 주문관리 리스트
    @Query("select new com.thisfit.shoppingmall.user.order.domain.dto.ItemInOrder(" +
                      "o.orderNo, o.itemNo, o.qty, o.userId, o.opt, o.orderState, o.orderDate, o.orderCancelDate, " +
                      "i.price, i.discount, i.name, i.thumbnail) " +
             "from Order o " +
             "join Item i on o.itemNo = i.no and o.orderState = :orderState ")
    List<ItemInOrder> findOrderMgtList(@Param("orderState") String order_state, Pageable pageable);

    // 총 주문수
    int countByOrderState(String order_state);
    
    // 선택 상품 배송/취소완료 (상태변경)을 위한 조회
    Order findByOrderNo(int order_no);

    // 전체 상품 배송/취소완료 (상태변경)을 위한 조회
    List<Order> findByOrderState(String order_state);

}
