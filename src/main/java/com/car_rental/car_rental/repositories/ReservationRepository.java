package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.static_data.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClientId(Long clientId);
    List<Reservation> findByCarId(Long carId);
    List<Reservation> findByReservationStatus(ReservationStatus status);
}
