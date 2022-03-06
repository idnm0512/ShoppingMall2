package com.thisfit.shoppingmall.user.wishlist.repository.datasource;

import com.thisfit.shoppingmall.user.wishlist.domain.dto.ItemInWishList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListJpaRepository extends JpaRepository<WishList, Integer> {

    // 관심상품 리스트
    @Query("select new com.thisfit.shoppingmall.user.wishlist.domain.dto.ItemInWishList(" +
                                                "w.wishlistNo, w.itemNo, w.userId, " +
                                                "i.price, i.discount, i.name, i.thumbnail) " +
             "from WishList w " +
             "join Item i on w.itemNo = i.no and w.userId = :userId ")
    List<ItemInWishList> findWishList(@Param("userId") String userId, Pageable pageable);

    // 전체 관심상품 수
    int countByUserId(String userId);

    // 관심상품 전체 삭제 (관심상품 비우기)
    void deleteByUserId(String userId);

    // 가장 최근 데이터 1개중 wishlist_no column만 (test에 쓰임)
    // 메서드명으로 쿼리 생성시 findFirstByOrderByWishlistNoDesc();
    // 하지만, 모든 row를 가져오기 때문에 @Query 사용
    @Query(value = "select wishlist_no from WISHLIST_TB " +
                    "order by wishlist_no desc " +
                    "limit 1",
           nativeQuery = true)
    int findLastWishlistNo();

}
