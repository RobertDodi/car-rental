package com.car_rental.car_rental.services;

import com.car_rental.car_rental.entites.Rental;
import com.car_rental.car_rental.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
    public class RentalService {

        private final RentalRepository rentalRepository;
        public Rental saveRental(Rental rental) {
            return rentalRepository.save(rental);
        }
        public List<Rental> getAllRentals() {
            return rentalRepository.findAll();
        }
        public Rental getRentalById(Long id) {
            return rentalRepository.findById(id).orElseThrow(()-> new RuntimeException("Rental not found"));
        }
        public List<Rental> findAllByName(String name){
            return rentalRepository.findAllByName(name);
        }
    }

