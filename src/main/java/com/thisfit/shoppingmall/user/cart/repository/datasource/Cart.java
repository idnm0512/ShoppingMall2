package com.thisfit.shoppingmall.user.cart.repository.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "CART_TB")
@Entity
public class Cart implements Serializable {

    /*
	 create table CART_TB (
		no int auto_increment,
	    user_id varchar(100),
	    item_no int,
	    opt varchar(200),
	    qty int,
	    constraint pk_cart primary key(no)
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private int cartNo;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    @Column(name = "user_id")
    private String userId;

    private String opt;

    // 장바구니 수량 변경을 위한 메서드
    public void changeQty(int qty) {
        this.qty = qty;
    }
    
}
