package com.evehicle.service;

import com.evehicle.entity.UserEntity;

public interface AuthenticationService {
	
	UserEntity customerLogin(String username,String password);

}
