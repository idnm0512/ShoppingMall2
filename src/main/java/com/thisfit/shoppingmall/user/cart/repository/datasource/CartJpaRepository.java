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
    List<ItemInCart> findCartList(@Param("userId") String user_id);

    // 장바구니 수량 변경을 위한 조회
    Cart findByCartNo(int cart_no);

    // 장바구니 전체 상품 삭제 (장바구니 비우기)
    void deleteByUserId(String user_id);

}
