package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
