package com.thisfit.shoppingmall.user.login.repository.datasource;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginJpaRepository extends JpaRepository<User, Integer> {

    // 로그인
    @Query("select u.id from User u " +
            "where u.id = :#{#user.id} and u.pwd = :#{#user.pwd} and u.state = true")
    String findLoginId(@Param("user") User user);

    // 아이디 찾기
    @Query("select u.id from User u " +
            "where u.name = :name and u.email = :email")
    String findId(@Param("name") String name, @Param("email") String email);

    // 비밀번호 찾기
    @Query("select u.pwd from User u " +
            "where u.id = :id and u.email = :email")
    String findPwd(@Param("id") String id, @Param("email") String email);

}
