package com.car_rental.car_rental.repositories;


import com.car_rental.car_rental.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
