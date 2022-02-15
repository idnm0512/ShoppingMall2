package com.thisfit.shoppingmall.admin.qnamgt.repository.datasource;

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
@Table(name = "QNA_ANSWER_TB")
@Entity
public class QNAMgtAnswer implements Serializable {

    /*
	 create table QNA_ANSWER_TB (
		re_no int auto_increment,
	    qno int,
	    re_title varchar(200),
	    re_content varchar(2000),
	    re_writer varchar(100),
	    re_regdate timestamp default now(),
	    re_update_date timestamp default now(),
	    constraint pk_qna_answer primary key(re_no)
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_no")
    private int reNo;

    private int qno;

    @Column(name = "re_title")
    private String reTitle;

    @Column(name = "re_content")
    private String reContent;

    @Column(name = "re_writer")
    private String reWriter;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "re_regdate")
    private LocalDateTime reRegdate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "re_update_date")
    private LocalDateTime reUpdateDate;

    // 답변수정을 위한 메서드
    public void changeReContent(String reContent) {
        this.reContent = reContent;
    }

}
