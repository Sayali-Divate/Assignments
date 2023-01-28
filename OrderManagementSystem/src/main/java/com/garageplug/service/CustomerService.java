package com.garageplug.service;

import com.garageplug.model.Customer;
import com.garageplug.model.CustomerDTO;
import com.garageplug.model.LoginDTO;

public interface CustomerService {

	Customer createAccount(CustomerDTO user) throws Exception;
	
	String loginUser(LoginDTO user) throws Exception;
	
	void sendMail(Integer numberOfOrders);
	
	
}
