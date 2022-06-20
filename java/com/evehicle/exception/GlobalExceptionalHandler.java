package com.evehicle.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(value = VehicleNotFoundException.class)
	public ResponseEntity<String> handleVehicleNotFoundException(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CartNotFoundException.class)
	public ResponseEntity<String> handleCartNotFoundException(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmptyCartException.class)
	public ResponseEntity<String> handleEmptyCartFoundException(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<String> handleOrderNotFoundException(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderFailedException.class)
	public ResponseEntity<String> handleOrderFailedException(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserNameNotExistingException.class)
	public ResponseEntity<String> handleUserNotExistingException(Exception e) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		return responseEntity;
	}	
	
	@ExceptionHandler(value = AuthenticationFailedException.class)
	public ResponseEntity<String> handleAuthenticationFailedException(Exception e) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		return responseEntity;
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String , Object> body = new LinkedHashMap<>();
		body.put("status",status.value());
		
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
		body.put("errors", errors);
		
		return new ResponseEntity<>(body,headers,status);
	}
	

}
