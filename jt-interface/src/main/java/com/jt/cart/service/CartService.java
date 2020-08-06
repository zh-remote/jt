package com.jt.cart.service;

import java.util.List;

import com.jt.cart.pojo.Cart;

public interface CartService {

	List<Cart> findCartByUserId(Long userId);

	void saveCart(Cart cart);

	/**
	 * 更新购物车记录
	 * @param cart
	 */
	void updateCart(Cart cart);

}
