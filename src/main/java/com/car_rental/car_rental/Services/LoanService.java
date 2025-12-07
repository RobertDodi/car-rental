package com.car_rental.car_rental.Services;

import com.car_rental.car_rental.repositories.LoanRepository;
import com.car_rental.car_rental.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

}
