package com.demo.SpringBootApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SpringBootApp.model.Customer;
import com.demo.SpringBootApp.service.CustomerService;
import com.demo.SpringBootApp.util.CustomErrorType;

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
		
		logger.info("Getting all customer profiles.");
		
		List<Customer> customerList = customerService.findAll();
		

		if(null == customerList || customerList.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info("Customer profiles size:" + customerList.size());

		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	/* Rest API to retrieve single customer profile. */
	@RequestMapping(value="/customer/{id}", method=RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id){
		logger.info("Getting customer profile for id: " + id);

		
		Customer customer = customerService.findById(id);
		
		if(null == customer ){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info("Customer name: " + customer.getFirstName() +" " +customer.getLastName());
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	/* Rest API to delete single customer profile. */
	@RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
		logger.info("Getting customer profile for id: " + id);

		
		Customer customer = customerService.findById(id);
		
		if(null == customer ){
			logger.error("No Customer found for this id: " + id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info("Deleting Customer profile with name: " + customer.getFirstName() +" " +customer.getLastName());
		
		customerService.deleteCustomerById(id);
		
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
	
	/* Rest API to add new customer. */
	@RequestMapping(value="/customer/", method=RequestMethod.POST)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		logger.info("Creating customer record: " + customer.toString());

		
		if(customerService.isCustomeExist(customer)) {
			logger.error("Unable to create customer. A customer with name already exist", customer.getFirstName());
			 return new ResponseEntity(new CustomErrorType("Unable to create. A User with first name " + 
					 customer.getFirstName() + " already exist."),HttpStatus.CONFLICT);
		}
		
		customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>( HttpStatus.CREATED);
	}
	
	/* Rest API to update exsiting customer. */
	 @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Customer customer) {
	        logger.info("Updating Customer with id: ", id);
	 
	        Customer currentCustomer = customerService.findById(id);
	 
	        if (currentCustomer == null) {
	            logger.error("Unable to update customer with id not found. ", id);
	            return new ResponseEntity(new CustomErrorType("Unable to upate customer with id " + id + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currentCustomer.setFirstName(customer.getFirstName());
	        currentCustomer.setLastName(customer.getLastName());
	        currentCustomer.setEmail(customer.getEmail());
	 
	        customerService.updateCustomer(currentCustomer);
	        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	    }
	
}
