package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import com.thisfit.shoppingmall.user.login.domain.dto.LoginRequest;
import com.thisfit.shoppingmall.user.login.domain.usecase.LoginUseCase;
import com.thisfit.shoppingmall.user.login.repository.datasource.LoginJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
// 별다른 설정없이 @SpringBootTest를 사용할 경우 H2 Database를 자동으로 실행한다.
@SpringBootTest
public class LoginUseCaseTest {

    @Autowired
    private LoginUseCase loginUseCase;

    @Autowired
    private LoginJpaRepository loginJpaRepository;

    private String id = "id123";
    private String pwd = "pwd123!!!";
    private String name = "name";
    private String email = "email123@gmail.com";

    private boolean state = true;

    @Before
    public void saveUser() {
        loginJpaRepository.save(User.builder()
                                    .id(id)
                                    .pwd(pwd)
                                    .name(name)
                                    .email(email)
                                    .state(state)
                                    .build());
    }

    @After
    public void cleanup() {
        loginJpaRepository.deleteAll();
    }

    // 로그인
    @Test
    public void loginTest() {
        // given
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setId(id);
        loginRequest.setPwd(pwd);

        // when
        String resultId = loginUseCase.getUserId(loginRequest);

        // then
        assertThat(resultId).isEqualTo(id);
    }

    // 아이디 찾기
    @Test
    public void findIdTest() {
        // given + when
        String resultId = loginUseCase.findId(name, email);

        // then
        assertThat(resultId).isEqualTo(id);
    }

    // 비밀번호 찾기
    @Test
    public void findPwdTest() {
        // given + when
        String resultPwd = loginUseCase.findPwd(id, email);

        // then
        List<User> userList = loginJpaRepository.findAll();

        User user = userList.get(0);

        assertThat(resultPwd).isEqualTo(user.getPwd());
    }

}
