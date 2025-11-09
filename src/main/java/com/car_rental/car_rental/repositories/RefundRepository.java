package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
