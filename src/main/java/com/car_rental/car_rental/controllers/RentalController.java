package com.car_rental.car_rental.controllers;

import com.car_rental.car_rental.services.RentalService;
import com.car_rental.car_rental.entites.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor

public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }
}

