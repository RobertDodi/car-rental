package com.car_rental.car_rental.Services;


import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
    public Employee findById(Long id){
        return employeeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Employee with id" +id+ " not found"));
    }

}
