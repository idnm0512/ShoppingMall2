package com.thisfit.shoppingmall.user.myinfo.repository.datasource;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyInfoJpaRepository extends JpaRepository<User, Integer> {

    // 내 정보 보기 & 각종 수정을 위한 조회
    User findById(String id);

}
