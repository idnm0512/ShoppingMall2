package com.thisfit.shoppingmall.domain.dto;

import com.thisfit.shoppingmall.user.login.domain.dto.LoginRequest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginRequestTest {

    // lombok이 제대로 작동하는지 LoginRequest의 @Getter/@Setter를 통해 Test
    @Test
    public void lombokTestWithLoginRequest() {
        // given
        String id = "testId";
        String pwd = "testPwd";

        // when
        LoginRequest loginRequest = new LoginRequest();

        // Check해야할 필드가 많다면 생성자 + builder()를 사용해도됨
        // success
        loginRequest.setId(id);
        loginRequest.setPwd(pwd);

        // fail
//        loginRequest.setId("failId");
//        loginRequest.setPwd("failPwd");

        // then
        assertThat(loginRequest.getId()).isEqualTo(id);
        assertThat(loginRequest.getPwd()).isEqualTo(pwd);
    }

}
