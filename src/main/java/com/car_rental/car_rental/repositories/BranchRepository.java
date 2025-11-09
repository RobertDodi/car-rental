package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
}
