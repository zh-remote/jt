package com.jt.cart.mapper;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart> {

	Cart findCartByUI(Cart cart);

	/**
	 * 根据userId和itemId更新数据
	 * @param cart
	 */
	void updateCartByUI(Cart cart);

}
