package com.evehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evehicle.entity.OrderEntity;
import com.evehicle.entity.PaymentEntity;
import com.evehicle.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/checkout/{cartId}")
	public ResponseEntity<OrderEntity> palceOrder(@PathVariable("cartId") int cartId, @RequestBody PaymentEntity paymentEntity) {
		
		OrderEntity orderEntity = orderService.checkOut(cartId, paymentEntity);
		
		return new ResponseEntity<>(orderEntity,HttpStatus.CREATED);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderEntity> getOrderDetails(@PathVariable("orderId") int orderId) {
		
		OrderEntity orderEntity = orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(orderEntity,HttpStatus.OK);
	}
}
