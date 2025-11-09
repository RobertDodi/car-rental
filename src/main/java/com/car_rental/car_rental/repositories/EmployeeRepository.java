package com.car_rental.car_rental.repositories;


import com.car_rental.car_rental.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByUsername(String username);
    Optional<Employee> findByUsername(String username);
}
