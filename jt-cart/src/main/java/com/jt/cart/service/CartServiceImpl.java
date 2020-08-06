package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;

//@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> findCartByUserId(Long userId) {
		Cart cart =new Cart();
		cart.setUserId(userId);
		List<Cart> select = cartMapper.select(cart);
		return select;
	}
	
	@Override
	public void saveCart(Cart cart) {
		Cart cartDB =cartMapper.findCartByUI(cart);
		if(cartDB==null) {
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			int num =cart.getNum()+cartDB.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cartDB);
		}
	}

	@Override
	public void updateCart(Cart cart) {
		cartMapper.updateCartByUI(cart);
		System.out.println("ssss"+cart);
	}

}
