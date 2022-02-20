package com.thisfit.shoppingmall.util.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    // 메인 페이지 진입
    @GetMapping
    public String mainGet() {
        log.info("메인 페이지 진입");

        return "/main";
    }

}
