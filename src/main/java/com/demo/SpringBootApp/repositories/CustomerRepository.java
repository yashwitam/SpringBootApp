package com.demo.SpringBootApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.SpringBootApp.model.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long>{

	Customer findByFirstName(String firstName);
}
