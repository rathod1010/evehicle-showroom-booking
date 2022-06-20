package com.evehicle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evehicle.entity.CartEntity;
import com.evehicle.entity.OrderEntity;
import com.evehicle.entity.UserEntity;
import com.evehicle.exception.UserNotFoundException;
import com.evehicle.model.User;
import com.evehicle.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {

		UserEntity userEntity = convertUserModelToEntity(user);

		CartEntity cartEntity = new CartEntity();
		userEntity.setCart(cartEntity);
		UserEntity newUserEntity = userRepository.save(userEntity);

		return convertUserEntityToModel(newUserEntity);
	}

	@Override
	public User getUserById(int userId) {

		Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

		if (optionalUserEntity.isEmpty()) {
			throw new UserNotFoundException("User Not existing with id: " + userId);
		}

		UserEntity userEntity = optionalUserEntity.get();

		return convertUserEntityToModel(userEntity);
	}

	@Override
	public List<User> getAllUsers() {

		List<UserEntity> userEntities = userRepository.findAll();

		List<User> users = new ArrayList<>();

		userEntities.forEach(entity -> {
			User user = convertUserEntityToModel(entity);
			users.add(user);
		});

		return users;
	}

	private UserEntity convertUserModelToEntity(User user) {

		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setUserName(user.getUserName());
		userEntity.setEmail(user.getEmail());
		userEntity.setMobile(user.getMobile());
		userEntity.setPincode(user.getPincode());
		userEntity.setCity(user.getCity());
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());

		return userEntity;
	}

	private User convertUserEntityToModel(UserEntity userEntity) {

		User user = new User();
		user.setUserId(userEntity.getUserId());
		user.setUserName(userEntity.getUserName());
		user.setEmail(userEntity.getEmail());
		user.setMobile(userEntity.getMobile());
		user.setPincode(userEntity.getPincode());
		user.setCity(userEntity.getCity());
		user.setUsername(userEntity.getUsername());
		user.setPassword(userEntity.getPassword());
		

		return user;
	}

	@Override
	public List<OrderEntity> getAllOrdersByUser(int userId) {

		Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

		if (optionalUserEntity.isEmpty()) {
			throw new UserNotFoundException("User Not existing with id: " + userId);
		}

		UserEntity userEntity = optionalUserEntity.get();

		return userEntity.getOrders();
	}

	@Override
	public CartEntity getCartDetailsByUser(int userId) {

		Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

		if (optionalUserEntity.isEmpty()) {
			throw new UserNotFoundException("User Not existing with id: " + userId);
		}

		UserEntity userEntity = optionalUserEntity.get();

		return userEntity.getCart();
	}

}
