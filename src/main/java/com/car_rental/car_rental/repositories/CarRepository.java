package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBranchId(Long branchId);
}
