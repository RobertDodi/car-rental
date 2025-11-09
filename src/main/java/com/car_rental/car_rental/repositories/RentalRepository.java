package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
