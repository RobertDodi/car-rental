package com.car_rental.car_rental.Services;

import com.car_rental.car_rental.entites.Customer;
import com.car_rental.car_rental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer with id" +id + " not found"));
    }

}
