package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.static_data.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClientId(Long clientId);
    List<Reservation> findByCarId(Long carId);

    List<Reservation> findAllByStatus(ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.car.id = :carId " +
           "AND r.status = 'BOOKED' " +
           "AND ((r.dateFrom <= :endDate AND r.dateTo >= :startDate))")
    List<Reservation> findOverlappingReservations(
            @Param("carId") Long carId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Reservation> findAllByClient_Email(String clientEmail);
}
