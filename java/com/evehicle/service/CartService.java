package com.evehicle.service;

import com.evehicle.entity.CartEntity;

public interface CartService {
	
	public CartEntity addToCart(int vehicleId,int quantity, int userId);

	public CartEntity getCartById(int cartId);

}
