package com.thisfit.shoppingmall.user.cart.repository;

import java.util.List;

import com.thisfit.shoppingmall.user.cart.domain.dto.ItemInCart;
import com.thisfit.shoppingmall.user.cart.domain.repository.CartGateway;
import com.thisfit.shoppingmall.user.cart.domain.vo.CartAddItemVO;
import com.thisfit.shoppingmall.user.cart.repository.datasource.Cart;
import com.thisfit.shoppingmall.user.cart.repository.datasource.CartJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartJpa implements CartGateway {

	@Autowired
	private CartJpaRepository cartJpaRepository;
	
	// 장바구니 리스트
	@Override
	public List<ItemInCart> getCartList(String user_id) {
		return cartJpaRepository.findCartList(user_id);
	}
	
	// 장바구니 담기
	@Override
	@Modifying
	@Transactional
	public void addItemInCart(CartAddItemVO cartAddItemVO) {
		Cart cart = Cart.builder()
						.userId(cartAddItemVO.getUser_id())
						.itemNo(cartAddItemVO.getItem_no())
						.opt(cartAddItemVO.getOpt())
						.qty(cartAddItemVO.getQty())
						.build();

		cartJpaRepository.save(cart);
	}
	
	// 장바구니 상품 수량 변경
	@Override
	@Modifying
	@Transactional
	public void modifyItemQty(int qty, int cart_no) {
		Cart cart = cartJpaRepository.findByCartNo(cart_no);

		cart.changeQty(qty);

		cartJpaRepository.save(cart);
	}
	
	// 장바구니 선택 상품 삭제
	@Override
	@Modifying
	@Transactional
	public void deleteItemInCart(int cart_no) {
		cartJpaRepository.deleteById(cart_no);
	}
	
	// 장바구니 전체 상품 삭제 (장바구니 비우기)
	@Override
	@Modifying
	@Transactional
	public void deleteAllItemInCart(String user_id) {
		cartJpaRepository.deleteByUserId(user_id);
	}
	
}
