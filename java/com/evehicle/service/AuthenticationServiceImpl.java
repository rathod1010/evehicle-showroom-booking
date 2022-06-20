package com.evehicle.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.evehicle.entity.Customer;
import com.evehicle.entity.UserEntity;
import com.evehicle.exception.AuthenticationFailedException;
import com.evehicle.exception.UserNameNotExistingException;
import com.evehicle.repository.CustomerRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserEntity customerLogin(String username, String password) {

		Optional<UserEntity> optionalCustomer = customerRepository.findByUsername(username);

		if (optionalCustomer.isEmpty()) {
			throw new UserNameNotExistingException("Username is not existing.");
		}

		Optional<UserEntity> optionalCustomer2 = customerRepository.findByUsernameAndPassword(username, password);

		if (optionalCustomer2.isEmpty()) {
			throw new AuthenticationFailedException("Authentication failed");
		}

		UserEntity userEntity = optionalCustomer2.get();

		return userEntity;
	}

}
