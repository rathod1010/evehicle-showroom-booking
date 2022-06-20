package com.evehicle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evehicle.entity.UserEntity;
import com.evehicle.model.LoginRequest;
import com.evehicle.model.LoginResponse;
import com.evehicle.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/customer/login")
	public ResponseEntity<LoginResponse> singin(@Valid @RequestBody LoginRequest loginReq) {

		UserEntity userEntity =  authenticationService.customerLogin(loginReq.getUserName(),loginReq.getPassword());

		LoginResponse loginResp = new LoginResponse();
		loginResp.setUserId(userEntity.getUserId());
		loginResp.setUserName(userEntity.getUserName());
		loginResp.setMobile(userEntity.getMobile());
		loginResp.setEmail(userEntity.getEmail());
		loginResp.setCity(userEntity.getCity());
		loginResp.setPincode(userEntity.getPincode());
		return new ResponseEntity<>(loginResp, HttpStatus.OK);

	}
}
