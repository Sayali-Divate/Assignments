package com.garageplug.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.garageplug.model.Customer;
import com.garageplug.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query("SELECT COUNT(o) FROM Orders o WHERE o.customer=?1")
	public Integer getCountOfOrders(Customer customer);
}
