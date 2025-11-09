package com.car_rental.car_rental.repositories;


import com.car_rental.car_rental.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
