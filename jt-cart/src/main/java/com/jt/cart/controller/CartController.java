package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
/*
	@Autowired
	private CartService cartService;
	private static final ObjectMapper objectMapper=new ObjectMapper();
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult findCartByUserId(@PathVariable Long userId) {
		try {
			List<Cart> cartList =cartService.findCartByUserId(userId);
			return SysResult.oK(cartList);
		} catch (Exception e) {
		}
		return SysResult.build(201, "购物车查询失败");
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCarts( String cartJSON) {
		try {
			Cart cart = objectMapper.readValue(cartJSON, Cart.class);
			cartService.saveCart(cart);
			return SysResult.oK( );
		} catch (Exception e) {
		}
		return SysResult.build(201, "购物车查询失败");
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateCart(Cart cart) {
		try {
			 System.out.println(cart.getNum());
			cartService.updateCart(cart);
			System.out.println(cart);
			return SysResult.oK( );
		} catch (Exception e) {
		}
		return SysResult.build(201, "购物车数量修改失败失败");
	}
	*/
}
