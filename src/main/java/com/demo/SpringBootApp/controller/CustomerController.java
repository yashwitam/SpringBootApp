package com.demo.SpringBootApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SpringBootApp.model.Customer;
import com.demo.SpringBootApp.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CustomerController {
	
	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;
	
	/* Rest API to get list of all customers. */
	@RequestMapping(value="/customer/", method=RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers(){
		
		List<Customer> customerList = customerService.findAll();
		if(null == customerList || customerList.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	
	
}
