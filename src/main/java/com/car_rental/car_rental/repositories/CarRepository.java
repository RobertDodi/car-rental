package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
