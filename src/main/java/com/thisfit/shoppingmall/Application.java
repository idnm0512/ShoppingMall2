package com.thisfit.shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@PropertySources({
        @PropertySource("classpath:mail.properties"),
        @PropertySource("classpath:s3.properties")
})
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        // Pageable 을 사용하여 페이징 처리를 할 때 시작을 1로 바꿔준다.
        // 좀 더 정확히 말하자면 원래는 0부터 시작이라 처음에 page=0으로 넘겨줘야하는데,
        // 이 설정을 통해서 들어오는 page 값에 -1을 해줌으로써
        // 넘기는 page 값과 화면상으로 보여지는 page 값을 1로 시작할 수 있게 된다.
        // 솔직히 확실하지 않다..
        return p -> p.setOneIndexedParameters(true);
    }

}
