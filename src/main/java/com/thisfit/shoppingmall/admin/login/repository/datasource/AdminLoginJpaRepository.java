package com.thisfit.shoppingmall.admin.login.repository.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginJpaRepository extends JpaRepository<AdminLogin, Integer> {

    @Query("select a.id from AdminLogin a " +
            "where a.id = :#{#admin.id} and a.pwd = :#{#admin.pwd}")
    String findAdminId(@Param("admin") AdminLogin adminLogin);
}
