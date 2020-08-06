package com.jt.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;
import com.jt.order.pojo.Order;
import com.jt.order.pojo.OrderItem;
import com.jt.order.pojo.OrderShipping;

//@Service
public class OrderServiceImplComsumer     {

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	


	public void saveOrder(Order order) {
		String orderId=order.getOrderId();
		Date date=new Date();
		order.setCreated(date);
		order.setUpdated(date);
		order.setStatus(1);
		orderMapper.insert(order); 
		System.out.println("订单入库成功");
		//实现订单物流入库
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		System.out.println("订单物流入库成功");
		//实现订单商品入库
		 
		List<OrderItem> orderItemsList=order.getOrderItems();
		for (OrderItem orderItem : orderItemsList) {
			System.out.println("商品id="+orderItem.getItemId());
			orderItem.setCreated(date);
			orderItem.setUpdated(date);
			orderItem.setOrderId(orderId);
			orderItemMapper.insert(orderItem);
		}
		System.out.println("订单商品入库成功");
		//return orderId;
	}

//	public List<Order> findAll(){
//		List<Order> select = orderMapper.select(null);
//		return select;
//	}
//	
//	@Override
//	public Order findOrderById(String orderId) {
//		Order order = orderMapper.selectByPrimaryKey(orderId);
//		OrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
//	    OrderItem orderItem=new OrderItem();
//	    orderItem.setOrderId(orderId);;
//		List<OrderItem> orderItemList = (List<OrderItem>) orderItemMapper.selectByPrimaryKey(orderId);
//		order.setOrderShipping(orderShipping);
//		order.setOrderItems(orderItemList);
//		return order;
//	}
}
