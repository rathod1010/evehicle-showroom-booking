package com.evehicle.service;

import java.util.List;

import com.evehicle.entity.CartEntity;
import com.evehicle.entity.OrderEntity;
import com.evehicle.model.User;

public interface UserService {

	User saveUser(User user);

	User getUserById(int userId);

	List<User> getAllUsers();

	List<OrderEntity> getAllOrdersByUser(int userId);

	CartEntity getCartDetailsByUser(int userId);

}
