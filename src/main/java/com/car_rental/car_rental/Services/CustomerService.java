package com.car_rental.car_rental.services;

import com.car_rental.car_rental.entites.Customer;
import com.car_rental.car_rental.entites.Rental;
import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Customer with id" +id + " not found"));
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + id));
    }

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new RuntimeException("Customer with email already exists");
        return customerRepository.save(customer);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);
        if (existing.getEmail().equals(updatedCustomer.getEmail()))
            return save(existing, updatedCustomer);
        else if (customerRepository.existsByEmail(updatedCustomer.getEmail())) {
            throw new RuntimeException("Customer with email already exists");
        } else
            return save(existing, updatedCustomer);
    }

    private Customer save(Customer existing, Customer updatedCustomer) {
        existing.setFirstName(updatedCustomer.getFirstName());
        existing.setLastName(updatedCustomer.getLastName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setLocation(updatedCustomer.getLocation());
        existing.setReservations(updatedCustomer.getReservations());
        existing.setRental(updatedCustomer.getRental());
        return customerRepository.save(existing);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NoSuchElementException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    // Get reservations for a customer
    public List<Reservation> getReservationsForCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getReservations();
    }

    // Get rental assigned to a customer
    public List<Customer> getCustomersByRentalId(Long rentalId) {
        return customerRepository.findByRentalId(rentalId);
    }
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).
                orElseThrow(() -> new RuntimeException("Customer with email" +email + " not found"));
    }
}
