package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@RequestMapping("/show")
	public String findCartByUserId(Model model) {
		//从本地线程中获取用户数据
		Long userId=UserThreadLocal.get().getId();
		List<Cart> cartList =cartService.findCartByUserId(userId);
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	
	@RequestMapping("/add/{itemId}")
	public String SaveCart(@PathVariable Long itemId ,Cart cart) {
		//从本地线程中获取用户数据
		Long userId=UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.saveCart(cart);
		System.out.println(cart);
		return "redirect:/cart/show.html";
	}
	
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public String updateNum(@PathVariable Long itemId ,@PathVariable int num) {
		//从本地线程中获取用户数据
		Long userId=UserThreadLocal.get().getId();
		Cart cart =new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setNum(num);
		cartService.updateCart(cart);
		return "ok";
	}
}
