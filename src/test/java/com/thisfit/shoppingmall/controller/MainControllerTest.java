package com.thisfit.shoppingmall.controller;

import com.thisfit.shoppingmall.util.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// Spring Container를 요구하는 Test는 @SpringBootApplication (Application Class)가 항상 로드된다.
// JPA에서 LocalDataTime을 자동으로 관리해주는 Auditing 기능을 사용하기 위해
// @SpringBootApplication (Application Class)에 @EnableJpaAuditing을 추가했는데,
// @WebMvcTest같은 단위 테스트는 JPA관련 Bean들을 Load하지 않기 때문에 Error가 발생한다.
// 그래서 @MockBean을 사용하거나 애초에 별도의 @Configuration 파일을 따로 만들어준다.
@MockBean(JpaMetamodelMappingContext.class)
@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void mainControllerTest() throws Exception {
        // success
        String main = "main";

        // fail
//        String main = "fail";

        mvc.perform(get("/main")) // 해당 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // HTTP 상태값이 200인지 Check
                .andExpect(view().name(main)); // 반환되는 View 이름 Check
    }

}
