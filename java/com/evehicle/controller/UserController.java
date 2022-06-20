package com.evehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evehicle.entity.CartEntity;
import com.evehicle.entity.OrderEntity;
import com.evehicle.model.User;
import com.evehicle.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<User> addCustomer(@RequestBody User user) {

		User newUser = userService.saveUser(user);

		return new ResponseEntity<>(newUser, HttpStatus.CREATED);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> fetchUserDetails(@PathVariable("userId") int userId) {

		User user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/all")
	public List<User> fetchAllUsers() {

		return userService.getAllUsers();
	}

	@GetMapping("/orders/{userId}")
	public List<OrderEntity> fetchAllOrdersByUser(@PathVariable int userId) {

		return userService.getAllOrdersByUser(userId);
	}

	@GetMapping("/cart/{userId}")
	public ResponseEntity<CartEntity> fetchCartDetails(@PathVariable int userId) {

		CartEntity cartEntity = userService.getCartDetailsByUser(userId);
		return new ResponseEntity<>(cartEntity, HttpStatus.OK);
	}

}
