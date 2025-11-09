package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "refunds")
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
