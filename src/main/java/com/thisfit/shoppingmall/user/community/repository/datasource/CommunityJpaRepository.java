package com.thisfit.shoppingmall.user.community.repository.datasource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityJpaRepository extends JpaRepository<Community, Integer> {

    // 커뮤니티 리스트
    Page<Community> findByCategory(String category, Pageable pageable);

    // 커뮤니티 상세보기
    Community findByNo(int no);

    // 전체 게시물 수
    int countByCategory(String category);

}
