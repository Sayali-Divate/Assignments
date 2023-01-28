package com.garageplug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garageplug.exception.LoginException;
import com.garageplug.model.OrderDTO;
import com.garageplug.service.OrderService;

@RestController
@RequestMapping("/garageplug")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<String> createOrder(OrderDTO order) throws LoginException{
		
		String msg = orderService.createOrder(order);
		
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
}
