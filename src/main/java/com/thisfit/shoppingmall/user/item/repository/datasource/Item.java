package com.thisfit.shoppingmall.user.item.repository.datasource;

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
@Table(name = "ITEM_TB")
@Entity
public class Item implements Serializable {

    /*
	 create table ITEM_TB (
	 	no int auto_increment,
	    category varchar(100),
	    category2 varchar(100),
	    name varchar(200),
	    price int,
	    discount int,
	    thumbnail varchar(1000),
	    content longtext,
	    constraint pk_item primary key(no)
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private int price;
    private int discount;

    private String category;
    private String category2;
    private String name;
    private String thumbnail;
    private String content;

    // 상품수정을 위한 메서드
    public void changeItem(int price, int discount, String category, String category2, String name,
                           String thumbnail, String content) {
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.category2 = category2;
        this.name = name;
        this.thumbnail = thumbnail;
        this.content = content;
    }

}
