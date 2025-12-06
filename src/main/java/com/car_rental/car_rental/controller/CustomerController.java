package com.car_rental.car_rental.controller;

import com.car_rental.car_rental.Services.CustomerService;
import com.car_rental.car_rental.entites.Customer;
import com.car_rental.car_rental.entites.Rental;
import com.car_rental.car_rental.entites.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 1. Get all customers
    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    // 2. Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // 3. Add a new customer
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 4. Update customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok(updated);
    }

    // 5. Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Get reservations for a customer
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsForCustomer(@PathVariable Long id) {
        List<Reservation> reservations = customerService.getReservationsForCustomer(id);
        return ResponseEntity.ok(reservations);
    }

    // 7. Get rental for a customer
    @GetMapping("/{id}/rental")
    public ResponseEntity <List<Customer>> getCustomersByRentalId(@PathVariable Long id) {
        List<Customer> customers = customerService.getCustomersByRentalId(id);
        return ResponseEntity.ok(customers);
    }

}

