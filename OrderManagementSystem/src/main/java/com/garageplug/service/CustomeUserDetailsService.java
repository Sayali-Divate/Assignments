package com.garageplug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.garageplug.model.Customer;
import com.garageplug.model.SecurityUser;
import com.garageplug.repository.CustomerRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer user = customerRepository.findByEmail(username);
		if(user==null) throw new UsernameNotFoundException("Email is not registered with Garage Plug");
		
		return new SecurityUser(user);
	}

}
