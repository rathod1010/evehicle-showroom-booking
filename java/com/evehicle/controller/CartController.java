package com.evehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evehicle.entity.CartEntity;
import com.evehicle.model.CartItemRequest;
import com.evehicle.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000/")
public class CartController {
	
	   @Autowired
	   private CartService cartService;
	   
	   @PutMapping("/addItem")
		public ResponseEntity<CartEntity> addItemToCart(@RequestBody CartItemRequest cartItemReq) {
					
			CartEntity updatedCart = cartService.addToCart(cartItemReq.getVehicleId(), cartItemReq.getQuantity(), cartItemReq.getUserId());
		
			return new ResponseEntity<>(updatedCart, HttpStatus.OK);
		}
	   
	   @GetMapping("/{cartId}")
		public ResponseEntity<CartEntity> displayBasket(@PathVariable("cartId") int cartId) {
			
			CartEntity cart = cartService.getCartById(cartId);
			
			return new ResponseEntity<>(cart,HttpStatus.OK);		
		}

}
