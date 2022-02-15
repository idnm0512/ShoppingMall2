package com.thisfit.shoppingmall.admin.login.repository.datasource;

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
@Table(name = "ADMIN_TB")
@Entity
public class AdminLogin implements Serializable {

    /*
     create table ADMIN_TB (
        no int auto_increment,
        id varchar(100),
        pwd varchar(200),
        name varchar(100),
        constraint pk_admin primary key(no)
     );
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String id;
    private String pwd;
    private String name;

}
