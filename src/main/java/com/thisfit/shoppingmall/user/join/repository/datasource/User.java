package com.thisfit.shoppingmall.user.join.repository.datasource;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
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
@Table(name = "USER_TB")
@Entity
public class User implements Serializable {

    /*
     create table USER_TB (
        no int auto_increment,
        id varchar(100),
        pwd varchar(200),
        name varchar(100),
        address varchar(200),
        address2 varchar(200),
        phone varchar(50),
        phone2 varchar(50),
        email varchar(150),
        birth varchar(50),
        joindate timestamp default now(),
        withdrawdate timestamp default null,
        state boolean default true,
        constraint pk_user primary key(no)
     );
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String id;
    private String pwd;
    private String name;
    private String address;
    private String address2;
    private String phone;
    private String phone2;
    private String email;
    private String birth;

    private boolean state;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime joindate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime withdrawdate;

    // 내 정보 수정을 위한 메서드
    public void changeMyInfo(String address, String address2, String phone, String phone2, String email, String birth) {
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
        this.phone2 = phone2;
        this.email = email;
        this.birth = birth;
    }

    // 비밀번호 변경을 위한 메서드
    public void changePwd(String pwd) {
        this.pwd = pwd;
    }

    // 회원탈퇴/비활성화 (상태변경)을 위한 메서드
    public void changeState(boolean state) {
        this.state = state;

        if (!state) {
            this.withdrawdate = LocalDateTime.now();
        } else {
            this.withdrawdate = null;
        }
    }

}
