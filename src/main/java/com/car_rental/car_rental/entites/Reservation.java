package com.car_rental.car_rental.entites;

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
    @Column(name = "date_of_booking")
    private LocalDate dateOfBooking;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Customer client;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Column(name = "date_from")
    private LocalDate dateFrom;
    @Column(name = "date to")
    private LocalDate dateTo;
    @ManyToOne
    @JoinColumn(name = "return_branch_id")
    private Branch returnBranch;
    private Integer amount;
}
