package com.evehicle.service;

import java.util.List;

import com.evehicle.entity.OrderEntity;
import com.evehicle.entity.PaymentEntity;

public interface OrderService {

	public OrderEntity checkOut(int cartId, PaymentEntity paymentEntity);

	public OrderEntity getOrderDetails(int orderId);

	public List<OrderEntity> getAllOrders();

}
