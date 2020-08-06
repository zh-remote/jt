package com.jt.order.service;

import java.util.List;

import com.jt.order.pojo.Order;

public interface OrderService {
	public List<Order> findAll();

	public String saveOrder(Order order);

	public Order findOrderById(String id);
}
