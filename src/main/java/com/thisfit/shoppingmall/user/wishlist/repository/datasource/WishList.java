package com.thisfit.shoppingmall.user.wishlist.repository.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "WISHLIST_TB")
@Entity
public class WishList {

    /*
	 create table WISHLIST_TB (
		wishlist_no int auto_increment,
	    item_no int,
	    user_id varchar(100),
	    constraint pk_wishlist primary key(no)
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_no")
    private int wishlistNo;

    @Column(name = "item_no")
    private int itemNo;

    @Column(name = "user_id")
    private String userId;

}
