package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.join.domain.dto.JoinRequest;
import com.thisfit.shoppingmall.user.join.domain.usecase.JoinUseCase;
import com.thisfit.shoppingmall.user.join.repository.datasource.JoinJpaRepository;
import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JoinUseCaseTest {

    @Autowired
    private JoinUseCase joinUseCase;

    @Autowired
    private JoinJpaRepository joinJpaRepository;

    @After
    public void cleanup() {
        joinJpaRepository.deleteAll();
    }

    private String id = "id123";
    private String pwd = "pwd123!!!";

    // 회원가입
    @Test
    public void joinTest() {
        // given
        JoinRequest joinRequest = new JoinRequest();

        joinRequest.setId(id);
        joinRequest.setPwd(pwd);

        // when
        joinUseCase.join(joinRequest);

        // then
        List<User> userList = joinJpaRepository.findAll();

        User user = userList.get(0);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPwd()).isEqualTo(pwd);
    }

    // 아이디 중복확인
    @Test
    public void idCheckTest() {
        // given
        joinJpaRepository.save(User.builder().id(id).build());

        // when
        String resultId = joinUseCase.idCheck(id);

        // then
        List<User> userList = joinJpaRepository.findAll();

        User user = userList.get(0);

        assertThat(resultId).isEqualTo(user.getId());
    }

}
