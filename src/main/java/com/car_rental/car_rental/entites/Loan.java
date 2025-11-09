package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
