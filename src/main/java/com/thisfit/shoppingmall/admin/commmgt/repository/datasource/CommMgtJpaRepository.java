package com.thisfit.shoppingmall.admin.commmgt.repository.datasource;

import com.thisfit.shoppingmall.user.community.repository.datasource.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommMgtJpaRepository extends JpaRepository<Community, Integer> {
    
    // 커뮤관리 수정을 위한 조회
    Community findByNo(int no);

}
