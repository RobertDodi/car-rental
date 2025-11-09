package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "date_of_rental")
    private LocalDate dateOfRental;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    private String comment;
}
