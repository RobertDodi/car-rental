package com.car_rental.car_rental.controllers;

import com.car_rental.car_rental.Services.LoanService;
import com.car_rental.car_rental.entites.Loan;
import com.car_rental.car_rental.models.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanDto loanDto) {
        Loan createdLoan = loanService.create(loanDto);
        return ResponseEntity.ok(createdLoan);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.findById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Loan>> getLoansByEmployee(@PathVariable Long employeeId) {
        List<Loan> loans = loanService.findByEmployeeId(employeeId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Loan>> getLoansByBranch(@PathVariable Long branchId) {
        List<Loan> loans = loanService.findAllByBranch(branchId);
        return ResponseEntity.ok(loans);
    }
}
