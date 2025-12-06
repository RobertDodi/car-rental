package com.car_rental.car_rental.repositories;


import com.car_rental.car_rental.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByRentalId(Long rentalId);

    boolean existsByEmail(String email);
}
