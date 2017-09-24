package com.demo.SpringBootApp.service;

import java.util.List;

import com.demo.SpringBootApp.model.Customer;

public interface CustomerService {

	void saveCustomer(Customer customer);
	void deleteCustomerById(Long id);
	void updateCustomer(Customer customer);
	List<Customer> findAll();
	Customer findById(Long id);
	Customer findByName(String name);
	boolean isCustomeExist(Customer customer);
	
}
