package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.cart.pojo.Cart;
import com.jt.order.pojo.Order;
import com.jt.cart.service.CartService;
import com.jt.order.service.OrderService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	public String toCreate(Model model) {
		List<Cart> carts = cartService.findCartByUserId(UserThreadLocal.get().getId());
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		try {
			Long userId=UserThreadLocal.get().getId();
			order.setUserId(userId);
			String orderId=orderService.saveOrder(order);
			if(StringUtils.isEmpty(orderId)) {
				throw new RuntimeException();
			}
			return SysResult.oK(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "提交订单失败");
	}
	
	@RequestMapping("/success")//http://www.jt.com/order/success.html?id=91594896462118
	public String success(String id,Model model) {
		Order order=orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "success";
	}
}
