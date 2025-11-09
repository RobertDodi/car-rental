package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.entites.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByReservationId(Long reservationId);

}
