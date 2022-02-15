package com.thisfit.shoppingmall.user.qna.repository.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "QNA_TB")
@Entity
public class QNA implements Serializable {

    /*
     create table QNA_TB (
        no int auto_increment,
        title varchar(200),
        content varchar(2000),
        writer varchar(100),
        regdate timestamp default now(),
        update_date timestamp,
        state boolean default false,
        constraint pk_notice primary key(no)
     );
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String title;
    private String content;
    private String writer;

    private boolean state;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regdate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    // 1:1문의 수정을 위한 메서드
    public void changeQNA(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 답변작성시 원글 state 변경을 위한 메서드
    public void changeState(boolean state) {
        this.state = state;
    }

}
