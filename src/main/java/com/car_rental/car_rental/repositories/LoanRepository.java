package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByEmployeeId(Long id);
}
