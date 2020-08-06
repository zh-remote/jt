package com.jt.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
 
//	@Autowired
//	private OrderService orderService;
//	
//	private static final ObjectMapper objectMapper=new ObjectMapper();
//	
//	/**
//	 * 实现订单入库
//	 * @param orderJSON
//	 * @return
//	 */
//	@RequestMapping("/create")
//	@ResponseBody
//	public SysResult saveOrder(String orderJSON) {
//		try {
//			Order order = objectMapper.readValue(orderJSON, Order.class);
//			String ordrId=orderService.saveOrder(order);
//			if(StringUtils.isEmpty(ordrId)) {
//				throw new RuntimeException();
//			}
//			return SysResult.oK(ordrId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return SysResult.build(201, "保存订单失败");
//	}
//	
//	@RequestMapping("/query/{id}")
//	@ResponseBody
//	public Order findOrderById(@PathVariable String id) {
//		Order order =null;
//	 	order=orderService.findOrderById(id);
//	 	return order;
//	} 
 
}
