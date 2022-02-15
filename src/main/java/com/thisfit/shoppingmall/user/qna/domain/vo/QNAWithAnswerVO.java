package com.thisfit.shoppingmall.user.qna.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class QNAWithAnswerVO {

    private final int no;
    private final int re_no;

    private final String title;
    private final String writer;
    private final String re_title;
    private final String re_writer;

    private final boolean state;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime update_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime re_update_date;

}
