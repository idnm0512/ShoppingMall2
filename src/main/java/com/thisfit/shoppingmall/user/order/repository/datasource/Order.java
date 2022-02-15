package com.thisfit.shoppingmall.user.order.repository.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "ORDER_TB")
@Entity
public class Order implements Serializable {

    /*
	 create table ORDER_TB (
		order_no int auto_increment,
	    user_id varchar(100),
	    item_no int,
	    opt varchar(200),
	    qty int,
	    order_state varchar(100),
	    order_date timestamp default now(),
	    order_cancel_date timestamp,
	    constraint pk_order primary key(order_no)
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private int orderNo;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    @Column(name = "user_id")
    private String userId;

    private String opt;

    @Column(name = "order_state")
    private String orderState;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_cancel_date")
    private LocalDateTime orderCancelDate;

    // 선택/전체 상품 주문취소/배송완료/취소완료 (상태변경)을 위한 메서드
    public void changeOrderState(String orderState) {
        this.orderState = orderState;

        if (orderState == "취소완료") {
            this.orderCancelDate = LocalDateTime.now();
        }
    }

}
