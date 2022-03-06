package com.thisfit.shoppingmall.user.cart.repository.datasource;

import com.thisfit.shoppingmall.user.cart.domain.dto.ItemInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Integer> {

    // 장바구니 리스트
    @Query("select new com.thisfit.shoppingmall.user.cart.domain.dto.ItemInCart(" +
                      "c.cartNo, c.itemNo, c.qty, c.userId, c.opt, " +
                      "i.price, i.discount, i.name, i.thumbnail) " +
             "from Cart c " +
             "join Item i on c.itemNo = i.no " +
            "where c.userId = :userId " +
           "order by c.cartNo desc")
    List<ItemInCart> findCartList(@Param("userId") String userId);

    // 장바구니 수량 변경을 위한 조회
    Cart findByCartNo(int cartNo);

    // 장바구니 전체 상품 삭제 (장바구니 비우기)
    void deleteByUserId(String userId);

    // 가장 최근 데이터 1개중 cart_no column만 (test에 쓰임)
    // 메서드명으로 쿼리 생성시 findFirstByOrderByCartNoDesc();
    // 하지만, 모든 row를 가져오기 때문에 @Query 사용
    @Query(value = "select cart_no from CART_TB " +
                    "order by cart_no desc " +
                    "limit 1",
           nativeQuery = true)
    int findLastCartNo();
}
