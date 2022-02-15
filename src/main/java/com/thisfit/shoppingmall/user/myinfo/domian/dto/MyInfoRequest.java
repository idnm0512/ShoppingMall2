package com.thisfit.shoppingmall.user.myinfo.domian.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyInfoRequest {

    private String id;
    private String pwd;
    private String name;
    private String address;
    private String address2;
    private String phone;
    private String phone2;
    private String email;
    private String birth;
}
