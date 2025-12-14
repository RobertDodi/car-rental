package com.car_rental.car_rental.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "refunds")
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDate dateOfReturn;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    private Double surcharge;
    private String comment;
}
