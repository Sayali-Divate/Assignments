package com.garageplug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.garageplug.model.Customer;
import com.garageplug.model.CustomerDTO;
import com.garageplug.model.CustomerType;
import com.garageplug.model.LoginDTO;
import com.garageplug.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public Customer createAccount(CustomerDTO user) throws Exception {
		
		
		//	Creating a customer and setting the required parameters	
		
		Customer cust = customerRepository.findByEmail(user.getEmail());
		if(cust!=null) throw new Exception("This email is already registered with garage plug");
		
		Customer customer = new Customer();
		customer.setFirstName(user.getFirstName());
		customer.setLastName(user.getLastName());
		customer.setEmail(user.getEmail());
		
		// Hashing the password
		
		String rawPassword = user.getPassword();
		customer.setPassword(passwordEncoder.encode(rawPassword));
		
		customer.setType(CustomerType.REGULAR);
		
		//  Customer is created
		
		//  Now we need to save the customer in the database
		
		return customerRepository.save(customer);
		
		//  Customer is saved successfully and return the customer details with the autogenerated customerId

	}
	
	@Override
	public String loginUser(LoginDTO user) throws Exception {
		
		try {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
			Authentication authentication =  authenticationManager.authenticate(authRequest);
			SecurityContext securityContext= SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);	
			return "User Logged in successfully";
		
		}catch(Exception ex) {
			throw new Exception("Please Enter Valid details");
		}
	}

	@Override
	public void sendMail(Integer numberOfOrders) {
		
		if(numberOfOrders==9) {
			
			System.out.println("You have placed 9 orders with us. Buy one more stuff and you will be\r\n"
					+ "promoted to Gold customer and enjoy 10% discounts!");
		}
		else if(numberOfOrders==19) {
			
			System.out.println("You have placed 19 orders with us. Buy one more stuff and you will be\r\n"
					+ "promoted to Platinum customer and enjoy 20% discounts!");
		}
		
	}

}
