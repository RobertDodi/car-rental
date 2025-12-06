package com.car_rental.car_rental.controller;

import com.car_rental.car_rental.Services.EmployeeService;
import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.models.EmployeeDto;
import com.car_rental.car_rental.static_data.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    // 2. Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    // 3. Get employee by username
    @GetMapping("/username/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
        Employee employee = employeeService.findByUsername(username);
        return ResponseEntity.ok(employee);
    }

    // 4. Get logged-in employee
    @GetMapping("/me")
    public ResponseEntity<Employee> getLoggedInEmployee() {
        Employee employee = employeeService.getLoggedInEmployee();
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 5. Get employees by branch ID (optionally filtered by position)
    @GetMapping("/by_branch/{branchId}")
    public ResponseEntity<List<Employee>> getEmployeesByBranch(
            @PathVariable Long branchId,
            @RequestParam(required = false) Position position) {
        List<Employee> employees = employeeService.findByBranchId(branchId, position);
        return ResponseEntity.ok(employees);
    }

    // 6. Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee created = employeeService.create(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 7. Update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(id); // Ensure DTO has the correct ID
        Employee updated = employeeService.update(employeeDto);
        return ResponseEntity.ok(updated);
    }

}

