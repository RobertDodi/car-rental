package com.car_rental.car_rental.repositories;

import com.car_rental.car_rental.entites.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
