package com.thisfit.shoppingmall.user.community.repository.datasource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityJpaRepository extends JpaRepository<Community, Integer> {

    // 커뮤니티 리스트
    Page<Community> findByCategory(String category, Pageable pageable);

    // 커뮤니티 상세보기
    Community findByNo(int no);

    // 전체 게시물 수
    int countByCategory(String category);

    // 가장 최근 데이터 1개중 no column만 (test에 쓰임)
    // 메서드명으로 쿼리 생성시 findFirstByOrderByNoDesc();
    // 하지만, 모든 row를 가져오기 때문에 @Query 사용
    @Query(value = "select no from COMMUNITY_TB " +
                    "order by no desc " +
                    "limit 1",
           nativeQuery = true)
    int findLastNo();

}
