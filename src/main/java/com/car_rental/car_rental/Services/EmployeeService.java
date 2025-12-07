package com.car_rental.car_rental.Services;


import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.models.EmployeeDto;
import com.car_rental.car_rental.repositories.EmployeeRepository;
import com.car_rental.car_rental.static_data.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private BranchService branchService;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
    public Employee findById(Long id){
        return employeeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Employee with id" +id+ " not found"));
    }
    public Employee findByUsername(String username){
        return employeeRepository.findByUsername(username).
                orElseThrow(() -> new RuntimeException("Employee with username" +username + " not found"));
    }
    public Employee getLoggedInEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Employee) {
                return (Employee) principal;
            }
        }
        return null;
    }

    public List<Employee> findByBranchId(Long branchId, Position position){
        if (position == null)
            return employeeRepository.findByBranch_Id(branchId);
        else
            return employeeRepository.findAllByBranch_IdAndPosition(branchId, position);
    }

    public Employee create(EmployeeDto employeeDto){
        if (employeeRepository.existsByUsername(employeeDto.getUsername())) {
            throw new RuntimeException("Employee with username " + employeeDto.getUsername() +" already exists");
        }
        Employee employee = new Employee();
        return save(employeeDto, employee);
    }

    public Employee update(EmployeeDto employeeDto){
        Employee employee = findById(employeeDto.getId());
        if (employee.getUsername().equals(employeeDto.getUsername())) {
            return save(employeeDto, employee);
        } else if (employeeRepository.existsByUsername(employeeDto.getUsername())) {
            throw new RuntimeException("Employee with username " + employeeDto.getUsername() +" already exists");
        } else {
            return save(employeeDto, employee);
        }
    }

    private Employee save(EmployeeDto employeeDto, Employee employee) {
        employee.setUsername(employeeDto.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPosition(employeeDto.getPosition());
        employee.setActive(true);
        Branch branch = branchService.findById(employeeDto.getBranchId());
        employee.setBranch(branch);
        return employeeRepository.save(employee);
    }
}
