package com.thisfit.shoppingmall.user.join.repository.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinJpaRepository extends JpaRepository<User, UserId> {

    // 아이디 중복확인
    @Query("select u.id from User u " +
            "where u.id = :id")
    String findId(@Param("id") String id);

}
