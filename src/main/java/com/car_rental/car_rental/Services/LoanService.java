package com.car_rental.car_rental.Services;

import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.entites.Loan;
import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.models.LoanDto;
import com.car_rental.car_rental.repositories.LoanRepository;
import com.car_rental.car_rental.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private EmployeeService employeeService;

    public Loan create(LoanDto loanDto) {
        Loan loan = new Loan();
        Employee employee = employeeService.getLoggedInEmployee();
        Reservation reservation = reservationService.findById(loanDto.getReservationId());
        loan.setEmployee(employee);
        loan.setReservation(reservation);
        loan.setDateOfRental(LocalDate.now());
        loan.setComment(loanDto.getComment());
        return loanRepository.save(loan);
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public List<Loan> findByEmployeeId(Long employeeId) {
        return loanRepository.findByEmployeeId(employeeId);
    }

    public List<Loan> findAllByBranch(Long employeeBranchId) {
        return loanRepository.findAllByEmployee_Branch_Id(employeeBranchId);
    }

}
