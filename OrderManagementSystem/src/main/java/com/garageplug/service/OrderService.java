package com.garageplug.service;

import com.garageplug.exception.LoginException;
import com.garageplug.model.OrderDTO;

public interface OrderService {	

	String createOrder(OrderDTO order) throws LoginException;
}
