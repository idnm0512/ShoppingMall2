package com.thisfit.shoppingmall.user.item.repository.datasource;

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
@Table(name = "ITEM_REVIEW_TB")
@Entity
public class ItemReview implements Serializable {

    /*
	 create table ITEM_REVIEW_TB (
		review_no int auto_increment,
	    item_no int,
	    user_id varchar(100),
	    content varchar(2000),
	    grade int,
	    review_img varchar(1000),
	    regdate timestamp default now(),
	    update_date timestamp default now(),
	    constraint pk_item_review primary key(review_no)
	 );
	 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
    private int reviewNo;

    @Column(name = "item_no")
    private int itemNo;

    private int grade;

    @Column(name = "user_id")
    private String userId;

    private String content;

    @Column(name = "review_img")
    private String reviewImg;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regdate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    
    // 상품 리뷰 수정을 위한 메서드
    public void changeItemReview(int grade, String content, String reviewImg) {
        this.grade = grade;
        this.content = content;
        this.reviewImg = reviewImg;
    }

}
