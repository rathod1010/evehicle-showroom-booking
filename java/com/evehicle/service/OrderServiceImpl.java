package com.evehicle.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evehicle.entity.CartEntity;
import com.evehicle.entity.CartItemEntity;
import com.evehicle.entity.OrderEntity;
import com.evehicle.entity.OrderItemEntity;
import com.evehicle.entity.PaymentEntity;
import com.evehicle.exception.CartNotFoundException;
import com.evehicle.exception.EmptyCartException;
import com.evehicle.exception.OrderFailedException;
import com.evehicle.exception.OrderNotFoundException;
import com.evehicle.repository.CartItemRepository;
import com.evehicle.repository.CartRepository;
import com.evehicle.repository.OrderRepository;
import com.evehicle.repository.PaymentRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderEntity checkOut(int cartId, PaymentEntity paymentEntity) {
		
		paymentEntity.setPaymentDate(LocalDate.now());

		Optional<CartEntity> optionalCartEntity = cartRepository.findById(cartId);
		
		if (optionalCartEntity.isEmpty()) {
			throw new CartNotFoundException("Cart not existing with id: " + cartId);
		}
		
		CartEntity cartEntity = optionalCartEntity.get();
		
		if(cartEntity.getCount() == 0) {
			throw new EmptyCartException("Your Cart is empty. Please add items to cart");
		}

		if (cartEntity.getCartTotal() > paymentEntity.getAmount()) {
			throw new OrderFailedException("Order failed");
		}
		
		paymentEntity.setPaymentStatus("SUCCESS");

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderDate(LocalDate.now());
		LocalDateTime dateTime = LocalDateTime.now();
		
		//order num generation
		String orderNum = "ORD"+dateTime.getYear()+dateTime.getMonthValue()+dateTime.getHour()+dateTime.getMinute();
		
		orderEntity.setOrderNum(orderNum);
		orderEntity.setOrderStatus("CONFIRMED");
		orderEntity.setTotalAmount(cartEntity.getCartTotal());

		List<CartItemEntity> cartItems = cartEntity.getCartItems();
		
		List<OrderItemEntity> orderItems = orderEntity.getOrderList();
		
		cartItems.forEach(item -> {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setAmount(item.getItemTotal());
			orderItemEntity.setQuantity(item.getQuantity());
			orderItemEntity.setVehicleId(item.getVehicleId());
			orderItemEntity.setOrder(orderEntity);

			orderItems.add(orderItemEntity);
		});

		orderEntity.setUser(cartEntity.getUser());

		OrderEntity newOrderEntity = orderRepository.save(orderEntity);

		paymentEntity.setOrderId(newOrderEntity.getOrderId());

		paymentRepository.save(paymentEntity);
		
		//Reset Basket
		cartEntity.setCount(0);
		cartEntity.setCartTotal(0);		
		cartRepository.save(cartEntity);
		
		//clear the Basket Items 
		
		cartItems.forEach(item-> {
			cartItemRepository.deleteById(item.getCartItemId());
		});
		
		return newOrderEntity;
		
	}

	@Override
	public OrderEntity getOrderDetails(int orderId) {
	
		Optional<OrderEntity> optionalOrder = orderRepository.findById(orderId);
		
		if(optionalOrder.isEmpty()) {
			throw new OrderNotFoundException("Order not existing with id: " + orderId);
		}
		return optionalOrder.get();
	}

	@Override
	public List<OrderEntity> getAllOrders() {
		
		return orderRepository.findAll();
	}

}
