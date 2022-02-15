package com.thisfit.shoppingmall.admin.itemmgt.repository.datasource;

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
@Table(name = "ITEM_OPT_TB")
@Entity
public class ItemMgtOpt implements Serializable {

    /*
	 create table ITEM_OPT_TB (
	 	opt_no int auto_increment,
		item_no int,
	    color varchar(100),
	    size varchar(100),
	    qty int
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opt_no")
    private int optNo;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    private String color;
    private String size;

}
