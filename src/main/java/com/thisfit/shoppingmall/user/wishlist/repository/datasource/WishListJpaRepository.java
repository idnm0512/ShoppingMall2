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
    List<ItemInWishList> findWishList(@Param("userId") String user_id, Pageable pageable);

    // 전체 관심상품 수
    int countByUserId(String user_id);

    // 관심상품 전체 삭제 (관심상품 비우기)
    void deleteByUserId(String user_id);

}
