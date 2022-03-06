package com.thisfit.shoppingmall.user.cart.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.cart.domain.dto.ItemInCart;
import com.thisfit.shoppingmall.user.cart.domain.repository.CartGateway;
import com.thisfit.shoppingmall.user.cart.domain.vo.CartAddItemVO;
import com.thisfit.shoppingmall.user.cart.repository.datasource.Cart;
import com.thisfit.shoppingmall.user.cart.repository.datasource.CartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartJpa implements CartGateway {

	private final CartJpaRepository cartJpaRepository;
	
	// 장바구니 리스트
	@Override
	public List<ItemInCart> getCartList(String userId) {
		return cartJpaRepository.findCartList(userId);
	}
	
	// 장바구니 담기
	@Override
	@Modifying
	@Transactional
	public void addItemInCart(CartAddItemVO cartAddItemVO) {
		Cart cart = Cart.builder()
						.userId(cartAddItemVO.getUserId())
						.itemNo(cartAddItemVO.getItemNo())
						.opt(cartAddItemVO.getOpt())
						.qty(cartAddItemVO.getQty())
						.build();

		cartJpaRepository.save(cart);
	}
	
	// 장바구니 상품 수량 변경
	@Override
	@Modifying
	@Transactional
	public void modifyItemQty(int qty, int cartNo) {
		Cart cart = cartJpaRepository.findByCartNo(cartNo);

		cart.changeQty(qty);

		cartJpaRepository.save(cart);
	}
	
	// 장바구니 선택 상품 삭제
	@Override
	@Modifying
	@Transactional
	public void deleteItemInCart(int cartNo) {
		cartJpaRepository.deleteById(cartNo);
	}
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	@Override
	@Modifying
	@Transactional
	public void deleteAllItemInCart(String userId) {
		cartJpaRepository.deleteByUserId(userId);
	}
	
}
