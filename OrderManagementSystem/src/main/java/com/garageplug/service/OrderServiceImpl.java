package com.garageplug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.garageplug.exception.LoginException;
import com.garageplug.model.Customer;
import com.garageplug.model.CustomerType;
import com.garageplug.model.OrderDTO;
import com.garageplug.model.Orders;
import com.garageplug.repository.CustomerRepository;
import com.garageplug.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerService customerService;

	@Override
	public String createOrder(OrderDTO od) throws LoginException {
		
		// getting the details of logged in user
		
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user==null) throw new LoginException("Login to continue");
		
		// getting customer details from database
		
		Customer customer = customerRepository.findByEmail(user.getUsername());
		
		// creating object of an order
		
		Orders order = new Orders();
		order.setCustomer(customer);
		Double amount = od.getOrderAmount();
		order.setOrderAmount(amount);
		
		// checking if the customer is eligible for any discount;
		
		if(customer.getType().equals(CustomerType.REGULAR)) order.setDiscount(0.0);
		else if(customer.getType().equals(CustomerType.GOLD)) {
			
			Double discount = amount*0.1; // 10% discount for gold users
			order.setDiscount(discount);			
			
		}		
		else {
			
			Double discount = amount*0.2; // 20% discount for platinum users
			order.setDiscount(discount);
		}
		
		
		// saving the order in database
		
		Orders newOrder = orderRepository.save(order);
		
		
		// cheking the number of orders for the customer to change the type of customer
		
		Integer num = orderRepository.getCountOfOrders(customer);
		
		// creating updated customer details because we can not save the above customer object as it is in persistent stage
		
		Customer newCustomer = new Customer();
		newCustomer.setCustomerId(customer.getCustomerId());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setPassword(customer.getPassword());
		
		// for setting the type we need to check the number of orders made till date
		
		if(num>=10) newCustomer.setType(CustomerType.GOLD);
		else if(num>=20) newCustomer.setType(CustomerType.PLATINUM);
		
		
		// now saving the updated customer
		
		customerRepository.save(newCustomer);
		
		
		// sending the mail to the customers regarding the benefits they can get
		
		customerService.sendMail(num);
		
		return "Order Placed Successfully....";
		
	}

}
