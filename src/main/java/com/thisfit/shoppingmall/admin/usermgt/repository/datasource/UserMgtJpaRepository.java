package com.thisfit.shoppingmall.admin.usermgt.repository.datasource;

import com.thisfit.shoppingmall.user.join.repository.datasource.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMgtJpaRepository extends JpaRepository<User, Integer> {

    // 회원 리스트
    Page<User> findByIdContains(String keyword, Pageable pageable);
    
    // 회원 활성화/비활성화 (상태변경)을 위한 조회
    // MyInfoJpaRepository -> 같은 메서드가 있지만,
    // 이 메서드 하나를 위해 Autowired 하는 것은 낭비라고 생각함
    User findById(String id);

    // 전체 회원 수
    int countByIdContains(String keyword);

}
