package com.demo.SpringBootApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.SpringBootApp.model.Customer;
import com.demo.SpringBootApp.repositories.CustomerRepository;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	public CustomerRepository customerRepository;

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.delete(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer findByName(String name) {
		return customerRepository.findByFirstName(name);
	}

	@Override
	public boolean isCustomeExist(Long id) {
		return customerRepository.exists(id);
	}

}
