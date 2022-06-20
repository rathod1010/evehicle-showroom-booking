package com.evehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evehicle.entity.CartEntity;
import com.evehicle.entity.CartItemEntity;
import com.evehicle.entity.UserEntity;
import com.evehicle.entity.VehicleEntity;
import com.evehicle.exception.CartNotFoundException;
import com.evehicle.exception.UserNotFoundException;
import com.evehicle.repository.CartItemRepository;
import com.evehicle.repository.CartRepository;
import com.evehicle.repository.UserRepository;
import com.evehicle.repository.VehicleRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public CartEntity addToCart(int vehicleId, int quantity, int userId) {

		// get cart from userId

		Optional<UserEntity> optionalUser = userRepository.findById(userId);

		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User is not existing with id: " + userId);
		}

		UserEntity user = optionalUser.get();

		CartEntity cart = user.getCart();

		// get vehicle from vehicleId

		Optional<VehicleEntity> optionalVehicle = vehicleRepository.findById(vehicleId);

		if (optionalVehicle.isEmpty()) {
			throw new CartNotFoundException("Vehicle is not existing with id: " + vehicleId);
		}

		VehicleEntity vehicle = optionalVehicle.get();

		double amount = vehicle.getVehiclePrice() * quantity;

		CartItemEntity cartItem = new CartItemEntity();
		cartItem.setVehicleId(vehicleId);
		cartItem.setQuantity(quantity);
		cartItem.setItemTotal(amount);
		cartItem.setCart(cart);

		List<CartItemEntity> cartItems = cart.getCartItems();
		cartItems.add(cartItem);

		double total = cartItems.stream().mapToDouble(item -> item.getItemTotal()).reduce(0, Double::sum);
		cart.setCartTotal(total);

		int count = cartItems.stream().map(item -> item.getQuantity()).reduce(0, Integer::sum);
		cart.setCount(count);

		cartItemRepository.save(cartItem);

		return cart;
	}

	@Override
	public CartEntity getCartById(int cartId) {

		Optional<CartEntity> optionalCart = cartRepository.findById(cartId);
		
		if (optionalCart.isEmpty()) {
			throw new CartNotFoundException("Cart is not existing with id: " + cartId);
		}

		return optionalCart.get();
	}

}
