package com.demo.SpringBootApp.service;

import com.demo.SpringBootApp.model.Customer;
import com.demo.SpringBootApp.repositories.CustomerRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
    }

    @After
    public void destroy() {
    }

    @Test
    public void testFindById() {

        Customer expected = new Customer();
        expected.setId(1L);
        expected.setFirstName("Bruce");
        expected.setLastName("Wayne");
        expected.setEmail("batman@gothamcity.com");
        expected.setAge(40);


        Mockito.when(this.customerRepository.findOne(expected.getId()))
                .thenReturn(expected);

        Customer result = this.customerService.findById(1L);

        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getEmail(), result.getEmail());
    }

    @Test
    public void testFindAll() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(2L);

        List<Customer> expected = new ArrayList<>();
        expected.add(customer1);
        expected.add(customer2);

        Mockito.when(this.customerRepository.findAll())
                .thenReturn(expected);

        List<Customer> results = this.customerService.findAll();

        Assert.assertNotNull(results);
        Assert.assertNotEquals(0, results.size());
        Assert.assertEquals(expected.get(0).getId(), results.get(0).getId());
        Assert.assertEquals(expected.get(1).getId(), results.get(1).getId());
    }

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {
        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }
    }

}
