package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import com.thisfit.shoppingmall.user.myinfo.domian.dto.MyInfoRequest;
import com.thisfit.shoppingmall.user.myinfo.domian.usecase.MyInfoUseCase;
import com.thisfit.shoppingmall.user.myinfo.domian.vo.MyInfoVO;
import com.thisfit.shoppingmall.user.myinfo.repository.datasource.MyInfoJpaRepository;
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
@SpringBootTest
public class MyInfoUseCaseTest {

    @Autowired
    private MyInfoUseCase myInfoUseCase;

    @Autowired
    private MyInfoJpaRepository myInfoJpaRepository;

    private String id = "id123";
    private String pwd = "pwd123!!!";
    private String birth = "123456";

    private boolean state = true;

    @Before
    public void saveUser() {
        myInfoJpaRepository.save(User.builder()
                                     .id(id)
                                     .pwd(pwd)
                                     .birth(birth)
                                     .state(state)
                                     .build());
    }

    @After
    public void cleanup() {
        myInfoJpaRepository.deleteAll();
    }

    // 내 정보 보기
    @Test
    public void getMyInfoTest() {
        // given + when
        MyInfoVO myInfoVO = myInfoUseCase.getMyInfo(id);

        // then
        assertThat(myInfoVO.getId()).isEqualTo(id);
        assertThat(myInfoVO.getPwd()).isEqualTo(pwd);
        assertThat(myInfoVO.getBirth()).isEqualTo(birth);
    }

    // 내 정보 수정
    @Test
    public void modifyMyInfoTest() {
        // given
        birth = "654321";

        MyInfoRequest myInfoRequest = new MyInfoRequest();

        myInfoRequest.setId(id);
        myInfoRequest.setBirth(birth);

        // when
        myInfoUseCase.modifyMyInfo(myInfoRequest);

        // then
        List<User> userList = myInfoJpaRepository.findAll();

        User user = userList.get(0);

        assertThat(user.getBirth()).isEqualTo(birth);
    }

    // 비밀번호 변경
    @Test
    public void modifyPwdTest() {
        // given
        pwd = "pwd123@@@";

        // when
        myInfoUseCase.modifyPwd(pwd, id);

        // then
        List<User> userList = myInfoJpaRepository.findAll();

        User user = userList.get(0);

        assertThat(user.getPwd()).isEqualTo(pwd);
    }

    // 회원탈퇴 (상태변경)
    @Test
    public void deleteUserTest() {
        // given
        state = false;

        // when
        myInfoUseCase.deleteUser(id);

        // then
        List<User> userList = myInfoJpaRepository.findAll();

        User user = userList.get(0);

        assertThat(user.isState()).isEqualTo(state);
    }

}
