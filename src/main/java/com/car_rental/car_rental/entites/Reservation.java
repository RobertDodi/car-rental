package com.car_rental.car_rental.entites;

import com.car_rental.car_rental.static_data.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfBooking;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Customer client;
    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @ManyToOne
    @JoinColumn(name = "return_branch_id")
    private Branch returnBranch;
    @ManyToOne
    @JoinColumn(name = "Loan_branch_id")

    private Branch loanBranch;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
