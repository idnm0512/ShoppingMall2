package com.thisfit.shoppingmall.user.login.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String id;
    private String pwd;
}
