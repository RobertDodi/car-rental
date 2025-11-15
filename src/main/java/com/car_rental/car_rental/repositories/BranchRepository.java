package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> findByName(String name);
    List<Branch> findByCityIgnoreCase(String city);
}

